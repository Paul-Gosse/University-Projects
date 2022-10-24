library(ggplot2)
library(dplyr)
library(lubridate)
library(fastICA)

base <- read.table("path-to/base.csv", TRUE, sep = ";", quote="\"", 
                   na.strings = TRUE, strip.white = TRUE, dec= ",",
                   comment.char = "$",blank.lines.skip = TRUE)
head(base)

base$date <- ymd(base$date)#permet de convertir en format date, ann????e-mois-jour
base$t <- ymd_hms(base$t)

base825 <- select(filter(base, id_sonde == 825), t, Teau)
names(base825)[2] <- 'Teau825'
base827 <- select(filter(base, id_sonde == 827), t, Teau)
names(base827)[2] <- 'Teau827'
base828 <- select(filter(base, id_sonde == 828), t, Teau)
names(base828)[2] <- 'Teau828'
base830 <- select(filter(base, id_sonde == 830), t, Teau)
names(base830)[2] <- 'Teau830'

head(base825)

TeauParHeure <- merge(base825, base827, by = "t")
TeauParHeure <- merge(TeauParHeure, base828, by = "t")
TeauParHeure <- merge(TeauParHeure, base830, by = "t")
 head(TeauParHeure)

 #### partie ACI 3 signaux ########## 
 
# plot(Teau825~t, data = TeauParHeure, xlim = NULL)
# lines(Teau827~t, data = TeauParHeure, col='blue')
# lines(Teau828~t, data = TeauParHeure, col='red')
# lines(Teau830~t, data = TeauParHeure, col='green')


set.seed(800)# on met 1 pour initier le processus
a <- fastICA(select(TeauParHeure, Teau825, Teau827, Teau828, Teau830), 3, alg.typ = "parallel", fun = "logcosh", alpha = 1,
             method = "R", row.norm = FALSE, maxit = 200,
             tol = 0.0001, verbose = TRUE)
#Uniquement possible avec base a variable quantitatives

#Matrice de passage a

A <- data.frame(a$S) #Création data frame matrice A

B <- cbind(select(TeauParHeure, t), A) #Binding des résultats par heure et date

# head(B)

B$comp1=a$A[1,1]*a$S[,1]
B$comp2=a$A[2,1]*a$S[,2]
B$comp3=a$A[3,1]*a$S[,3]

B$Teau825Recomp <- B$comp1+B$comp2+B$comp3+mean(TeauParHeure$Teau825)
summary(B$Teau825Recomp - TeauParHeure$Teau825) #la diff�rence entre l'originale et la recomposition de 825 est proche de z�ro

par(mfrow = c(3, 1))
plot(comp1~t, data = B, type = "l", col='blue', xlab = "", ylab = "Composante 1", main = "Premi�re composante extraite") #composante non-utilisable
plot(comp2~t, data = B, type = "l", col='green', xlab = "", ylab = "Composante 2", main = "Seconde composante extraite - influence saisonni�re") #composante p�riodique annuelle - influence nappe phr�atique
plot(comp3~t, data = B, type = "l", col='orange', xlab = "date en jour", ylab = "Composante 3", main = "Troisi�me composante extraite - influence des nappes phr�atiques") #composante p�riodique annuelle - influence saisonni�re �t�/hiver



