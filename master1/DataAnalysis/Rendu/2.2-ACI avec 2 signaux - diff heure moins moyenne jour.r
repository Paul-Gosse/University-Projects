library(ggplot2)
library(dplyr)
library(lubridate)
library(fastICA)

base <- read.table("path-to/base.csv", TRUE, sep = ";", quote="\"", 
                   na.strings = TRUE, strip.white = TRUE, dec= ",",
                   comment.char = "$",blank.lines.skip = TRUE)

base$date <- ymd(base$date)#permet de convertir en format date, ann????e-mois-jour
base$t <- ymd_hms(base$t)

moyenneJour825 <- aggregate(Teau~date, data = filter(base, id_sonde == 825), FUN = mean, na.rm = T)
moyenneJour827 <- aggregate(Teau~date, data = filter(base, id_sonde == 827), FUN = mean, na.rm = T)
moyenneJour828 <- aggregate(Teau~date, data = filter(base, id_sonde == 828), FUN = mean, na.rm = T)
moyenneJour830 <- aggregate(Teau~date, data = filter(base, id_sonde == 830), FUN = mean, na.rm = T)

base825 <- select(filter(base, id_sonde == 825), t, date, Teau)
names(base825)[3] <- 'Teau825'
base827 <- select(filter(base, id_sonde == 827), t, date, Teau)
names(base827)[3] <- 'Teau827'
base828 <- select(filter(base, id_sonde == 828), t, date, Teau)
names(base828)[3] <- 'Teau828'
base830 <- select(filter(base, id_sonde == 830), t, date, Teau)
names(base830)[3] <- 'Teau830'

names(moyenneJour825)[2] <- 'Teau825MoyenneJour'
names(moyenneJour827)[2] <- 'Teau827MoyenneJour'
names(moyenneJour828)[2] <- 'Teau828MoyenneJour'
names(moyenneJour830)[2] <- 'Teau830MoyenneJour'


base825 <- merge(base825, moyenneJour825, by = "date")
base825$diff825 <- base825$Teau825 - base825$Teau825MoyenneJour
base827 <- merge(base827, moyenneJour827, by = "date")
base827$diff827 <- base827$Teau827 - base827$Teau827MoyenneJour
base828 <- merge(base828, moyenneJour828, by = "date")
base828$diff828 <- base828$Teau828 - base828$Teau828MoyenneJour
base830 <- merge(base830, moyenneJour830, by = "date")
base830$diff830 <- base830$Teau830 - base830$Teau830MoyenneJour


# plot(diff825~t, data = base825, type="l")
# lines(diff827~t, data = base827, col = "green")
# lines(diff828~t, data = base828, col = "red")
# lines(diff830~t, data = base830, col = "blue")

diffHeureMoinsMoyenneJour <- cbind(select(base825, t, diff825), select(base827, diff827), select(base828, diff828), select(base830, diff830))
head(diffHeureMoinsMoyenneJour)

# tmp1 <- merge( select(base825, date, diff825), select(base827, date, diff827), by = "date")
# tpm2 <- merge( tmp1, select(base828, date, diff828), by = "date")
# head(tpm2)
# diffHeureMoinsMoyenneJour <- merge( tpm2, select(base830, date, diff830), by = "date")
# merge rempli la m??moire de mani??re folle, sans doute qu'il fait un produit cardinal des matrices avant de groupe by date

set.seed(800)# on met 1 pour initier le processus
a <- fastICA(select(diffHeureMoinsMoyenneJour, diff825, diff827, diff828, diff830), 2, alg.typ = "parallel", fun = "logcosh", alpha = 1,
             method = "R", row.norm = FALSE, maxit = 200,
             tol = 0.0001, verbose = TRUE)
#Uniquement possible avec base a variable quantitatives

#Matrice de passage a

A <- data.frame(a$S) #Cr????ation data frame matrice A

B <- cbind(select(diffHeureMoinsMoyenneJour, t), A) #Binding des r????sultats par heure et date
head(B)

B$comp1=a$A[1,1]*a$S[,1]
B$comp2=a$A[2,1]*a$S[,2]

B$Diff825Recomp <- B$comp1+B$comp2+mean(diffHeureMoinsMoyenneJour$diff825)
summary(B$Diff825Recomp - diffHeureMoinsMoyenneJour$diff825) #la diff??rence entre l'originale et la recomposition de 825 est proche de z??ro

par(mfrow = c(2, 1))
plot(comp1~t, data = B, type = "l", col = "blue", xlab = "", ylab = "Composante 1", main = "Premi??re composante extraite") #composante non-utilisable
plot(comp2~t, data = B, type = "l", col = "orange", xlab = "date en jour", ylab = "Composante 2", main = "Seconde composante extraite") #composante p??riodique annuelle - influence des saisons ete/hiver
