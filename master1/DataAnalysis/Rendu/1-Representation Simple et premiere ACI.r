library(ggplot2)
library(dplyr)
library(lubridate)
library(fastICA)

base <- read.table("C:/Users/aboit/Documents/uni/M1/analyse de donn�es/projet Touques/base.csv", TRUE, sep = ";", quote="\"", 
                   na.strings = TRUE, strip.white = TRUE, dec= ",",
                   comment.char = "$",blank.lines.skip = TRUE)

head(base)

base$date <- ymd(base$date)#permet de convertir en format date, année-mois-jour

Od1a825 <- filter(base, id_sonde==825)
Od1a827 <- filter(base, id_sonde==827)
Od1a828 <- filter(base, id_sonde==828)
Od1a830 <- filter(base, id_sonde==830)

Od1aa825 <- aggregate(Teau~date, data = Od1a825, FUN = mean, na.rm = T) #Moyenne Teau par date
Od1aa827 <- aggregate(Teau~date, data = Od1a827, FUN = mean, na.rm = T)
Od1aa828 <- aggregate(Teau~date, data = Od1a828, FUN = mean, na.rm = T)
Od1aa830 <- aggregate(Teau~date, data = Od1a830, FUN = mean, na.rm = T)

Od1bb825 <- aggregate(Tair.EOBS~date, data = Od1a825, FUN = mean, na.rm = T) #Moyenne Tair par date
Od1bb827 <- aggregate(Tair.EOBS~date, data = Od1a827, FUN = mean, na.rm = T)
Od1bb828 <- aggregate(Tair.EOBS~date, data = Od1a828, FUN = mean, na.rm = T)
Od1bb830 <- aggregate(Tair.EOBS~date, data = Od1a830, FUN = mean, na.rm = T)

Od1aa825$Teau825 <- Od1aa825$Teau #Renommage des colonnes Teau en fct de la sonde
Od1aa827$Teau827 <- Od1aa827$Teau
Od1aa828$Teau828 <- Od1aa828$Teau
Od1aa830$Teau830 <- Od1aa830$Teau

Od1bb825$Tair825 <- Od1bb825$Tair.EOBS #Renommage des colonnes Tair en fct de la sonde
Od1bb827$Tair827 <- Od1bb827$Tair.EOBS
Od1bb828$Tair828 <- Od1bb828$Tair.EOBS
Od1bb830$Tair830 <- Od1bb830$Tair.EOBS

par(mfrow = c(1,1))
plot(Teau825~date, data=Od1aa825, type="l", col="blue", main ="Evolution des Teau mesurés par 825") #graphe
plot(Teau827~date, data=Od1aa827, type="l", col="black", main ="Evolution des Teau mesurés par 827") #graphe
plot(Teau828~date, data=Od1aa828, type="l", col="red", main ="Evolution des Teau mesurés par 828") #graphe
plot(Teau830~date, data=Od1aa830, type="l", col="green", main ="Evolution des Teau mesurés par 830") #graphe

plot(Tair825~date, data=Od1bb825, type="l", col="blue", main ="Evolution des Tair mesurés par 825") #graphe
plot(Tair827~date, data=Od1bb827, type="l", col="black", main ="Evolution des Tair mesurés par 827") #graphe
plot(Tair828~date, data=Od1bb828, type="l", col="red", main ="Evolution des Tair mesurés par 828") #graphe
plot(Tair830~date, data=Od1bb830, type="l", col="green", main ="Evolution des Tair mesurés par 830") #graphe

#Représentation sur un même graphe Teau
par(mfrow = c(1,2))
plot(Teau825~date, type="l", main="Evolution des Teau", data=Od1aa825, col="blue", ylab="température")
lines(Teau827~date, type='l', data=Od1aa827, col="black")
lines(Teau828~date, type='l', data=Od1aa828, col="red")
lines(Teau830~date, type='l', data=Od1aa830, col="gold")
legend("bottomright", legend=c("825", "827", "828", "830"),
       col=c("blue", "black", "red", "gold"), lty=1, cex=0.5)

#Représentation sur un même graphe Tair
plot(Tair825~date, type="l", main="Evolution des Tair", data=Od1bb825, col="blue", ylab="température")
lines(Tair827~date, type='l', data=Od1bb827, col="black")
lines(Tair828~date, type='l', data=Od1bb828, col="red")
lines(Tair830~date, type='l', data=Od1bb830, col="gold")
legend("bottomright", legend=c("825", "827", "828", "830"),
       col=c("blue", "black", "red", "gold"), lty=1, cex=0.5)





#ACI pour Teau

#Fusion des données
mediumMerge1 <- merge(Od1aa825, Od1aa827, by = "date")
removeDuplicateMerge1 <- subset(mediumMerge1, select = -c(Teau.x, Teau.y)) #Remove doublons
head(mediumMerge1)
head(removeDuplicateMerge1)

mediumMerge2 <- merge(removeDuplicateMerge1, Od1aa828, by = "date")
removeDuplicateMerge2 <- subset(mediumMerge2, select = -c(Teau))
head(mediumMerge2)
head(removeDuplicateMerge2)

mediumMerge3 <- merge(mediumMerge2, Od1aa830, by = "date")
fullmerge <- removeDuplicateMerge2 <- subset(mediumMerge3, select = -c(Teau.x, Teau.y))
head(mediumMerge3)
head(fullmerge)

ACIReadyBaseTeau <- select(fullmerge, date,Teau825, Teau827, Teau828, Teau830) #Selection pour ACI
quantitativeOnlyBaseTeau <- select(ACIReadyBaseTeau, -date) #Base avec variables quantitatives uniquement
dateOnlyBase <- select(ACIReadyBaseTeau, date)
head(dateOnlyBase)
head(quantitativeOnlyBaseTeau)

summary(quantitativeOnlyBaseTeau) #Sommaire de la base obtenue

set.seed(800)# on met 1 pour initier le processus
a <- fastICA(quantitativeOnlyBaseTeau, 4, alg.typ = "parallel", fun = "logcosh", alpha = 1,
             method = "R", row.norm = FALSE, maxit = 200,
             tol = 0.0001, verbose = TRUE)
#Uniquement possible avec base a variable quantitatives

#Matrice de passage a
a$A

A <- data.frame(a$S) #Creation data frame matrice A

B <- cbind(dateOnlyBase, A) #Binding des résultats par date

par(mfrow = c(1, 2))
plot(a$X, main = "Donnees pre-processees")
plot(a$S, main = "Composantes de l'ACI")

B$comp1=a$A[1,1]*a$S[,1]
B$comp2=a$A[2,1]*a$S[,2]
B$comp3=a$A[3,1]*a$S[,3]
B$comp4=a$A[4,1]*a$S[,4]


par(mfrow = c(1,1))
plot(comp1~date, type="l", data=B, col="blue") #Variations marqué et périodique
plot(comp2~date, type="l", data=B, col="red") #Pic descendant en 2015, stable le reste du temps
plot(comp3~date, type="l", data=B, col="black") #Variations marqué et périodique
plot(comp4~date, type="l", data=B, col="green") #Variations faibles


B$Z825=B$comp1+B$comp2+B$comp3+B$comp4+mean(ACIReadyBaseTeau$Teau825)
#fastICA centre les données, il faut donc rajouter la moyenne de la série
B$diff825=B$Z825-ACIReadyBaseTeau$Teau825
summary(B$diff825)#0
#Toutes les composantes sont proches de 0

#Même principe pour les sondes 827, 828 et 830
B$Z827=B$comp1+B$comp2+B$comp3+B$comp4+mean(ACIReadyBaseTeau$Teau827)
B$diff827=B$Z827-ACIReadyBaseTeau$Teau827
summary(B$diff827)
#Différence légérement plus marqué que 825 mais moyenne reste égale à 0

B$Z828=B$comp1+B$comp2+B$comp3+B$comp4+mean(ACIReadyBaseTeau$Teau828)
B$diff828=B$Z828-ACIReadyBaseTeau$Teau828
summary(B$diff828)
#Différence similaire a 827, moyenne nulle.

B$Z830=B$comp1+B$comp2+B$comp3+B$comp4+mean(ACIReadyBaseTeau$Teau830)
B$diff830=B$Z830-ACIReadyBaseTeau$Teau830
summary(B$diff830)#0
#Différence similaire a 827 et 828, moyenne nulle.

#en différenciant les valeurs initiales et les valeurs estimées et en montrant que cette
#cette différence est nulle, on justifie qu'on a bien recomposé la série.





#ACI pour Tair.EOBS

#Fusion des données
mediumMerge1Tair <- merge(Od1bb825, Od1bb827, by = "date")
removeDuplicateMerge1Tair <- subset(mediumMerge1Tair, select = -c(Tair.EOBS.x, Tair.EOBS.y)) #Remove doublons
head(mediumMerge1Tair)
head(removeDuplicateMerge1Tair)

mediumMerge2Tair <- merge(removeDuplicateMerge1Tair, Od1bb828, by = "date")
removeDuplicateMerge2Tair <- subset(mediumMerge2Tair, select = -c(Tair.EOBS))
head(mediumMerge2Tair)
head(removeDuplicateMerge2Tair)

mediumMerge3Tair <- merge(mediumMerge2Tair, Od1bb830, by = "date")
fullmergeTair <- removeDuplicateMerge2Tair <- subset(mediumMerge3Tair, select = -c(Tair.EOBS.x, Tair.EOBS.y))
head(mediumMerge3Tair)
head(fullmergeTair)

ACIReadyBaseTair <- select(fullmergeTair, date,Tair825, Tair827, Tair828, Tair830) #Selection pour ACI
quantitativeOnlyBaseTair <- select(ACIReadyBaseTair, -date) #Base avec variables quantitatives uniquement
head(quantitativeOnlyBaseTair)

summary(quantitativeOnlyBaseTair) #Sommaire de la base obtenue

set.seed(800)# on met 1 pour initier le processus
b <- fastICA(quantitativeOnlyBaseTair, 4, alg.typ = "parallel", fun = "logcosh", alpha = 1,
             method = "R", row.norm = FALSE, maxit = 200,
             tol = 0.0001, verbose = TRUE)
#Uniquement possible avec base a variable quantitatives

#Matrice de passage a
b$A

C <- data.frame(b$S) #Création data frame matrice A

D <- cbind(dateOnlyBase, C) #Binding des résultats par date

par(mfrow = c(1,2))
plot(b$X, main = "Données pré-processés")
plot(b$S, main = "Composantes de l'ACI")

D$comp1=b$A[1,1]*b$S[,1]
D$comp2=b$A[2,1]*b$S[,2]
D$comp3=b$A[3,1]*b$S[,3]
D$comp4=b$A[4,1]*b$S[,4]

par(mfrow = c(1,1))
plot(comp1~date, type="l", data=D, col="blue") #Variations très faibles
plot(comp2~date, type="l", data=D, col="red") #Variations très faibles
plot(comp3~date, type="l", data=D, col="black") #Variations très faibles
plot(comp4~date, type="l", data=D, col="green") #Variations periodiques

D$Z825=D$comp1+D$comp2+D$comp3+D$comp4+mean(ACIReadyBaseTair$Tair825)
#fastICA centre les données, il faut donc rajouter la moyenne de la série
D$diff825=D$Z825-ACIReadyBaseTair$Tair825
summary(D$diff825)
#Toutes les composantes sont proches de 0

#Même principe pour les sondes 827, 828 et 830
D$Z827=D$comp1+D$comp2+D$comp3+D$comp4+mean(ACIReadyBaseTair$Tair827)
D$diff827=D$Z827-ACIReadyBaseTair$Tair827
summary(D$diff827)
#Différence légérement plus marqué que 825 mais moyenne reste égale à 0

D$Z828=D$comp1+D$comp2+D$comp3+D$comp4+mean(ACIReadyBaseTair$Tair828)
D$diff828=D$Z828-ACIReadyBaseTair$Tair828
summary(D$diff828)
#Différence similaire a 828, moyenne nulle.

D$Z830=D$comp1+D$comp2+D$comp3+D$comp4+mean(ACIReadyBaseTair$Tair830)
D$diff830=D$Z830-ACIReadyBaseTair$Tair830
summary(D$diff828)
#Différence similaire a 827 et 828, moyenne nulle.

#en différenciant les valeurs initiales et les valeurs estimées et en montrant que cette
#cette différence est nulle, on justifie qu'on a bien recomposé la série.





#Comparaison entre ACI Tair et ACI Teau

plot(comp3~date, type="l", data=D, col="black") #Variations très faibles Tair
lines(comp3~date, type="l", data=B, col="blue") #Variations très faibles Teau

dataFrameSignals <- data.frame(D$comp3, B$comp3)
head(dataFrameSignals)

cor.test(dataFrameSignals$D.comp3, dataFrameSignals$B.comp3, method="pearson")

regressionBetweenSignals <- lm(D.comp3 ~ B.comp3, data = dataFrameSignals)
summary(regressionBetweenSignals)

plot(D.comp3 ~ B.comp3, data=dataFrameSignals)
abline(regressionBetweenSignals, col = "red") 

E <- lowess(dataFrameSignals$D.comp3, dataFrameSignals$B.comp3, f = 0.33, iter = 1) #Lissage sur 33% des valeurs
E2 <- lowess(dataFrameSignals$D.comp3, dataFrameSignals$B.comp3, f = 0.66, iter = 1) #Lissage sur 66% des valeurs

plot(D.comp3 ~ B.comp3, data=dataFrameSignals)
plot(dataFrameSignals$D.comp3, dataFrameSignals$B.comp3, data=dataFrameSignals)
lines(E, col="magenta")
lines(E2, col="yellow")
legend("bottomright", legend=c("0.33", "0.66"),
       col=c("magenta", "yellow"), lty=1, cex=0.8)


library(corrplot)
corrplot(cor(dataFrameSignals)) #Diagramme de corrélation


hist(residuals(regressionBetweenSignals),col="yellow",freq=F)

densiteSignals <- density(residuals(regressionBetweenSignals)) #Estimation de la densité représenté par ces différentes valeurs

lines(densiteSignals, col = "red",lwd=3) # Superposer une ligne de densité à l'histogramme

#Les résidus suivent une loi normale


#Visualiser la donnée

par(mfrow=c(2,2))
#boxplot 
boxplot(ACIReadyBaseTeau[,c('Teau825','Teau827','Teau828','Teau830')],
        col = c("blue", "darkgreen", "gold", "magenta"),  
        main = paste("Teau.EOBS par sonde"),     
        ylab = "Température")      

boxplot(ACIReadyBaseTair[,c('Tair825','Tair827','Tair828','Tair830')],
        col = c("blue", "darkgreen", "gold", "magenta"),  
        main = paste("Tair.EOBS par sonde"),     
        ylab = "Température") 

hist(base$Teau, 
     col = c("orange"),
     main = paste("Histogramme pour la variable Teau"),
     ylab = "Effectifs",
     xlab = "T° de l'eau")

hist(base$Tair.EOBS, 
     col = c("orange"),
     main = paste("Histogramme pour la variable Tair.EOBS"),
     ylab = "Effectifs",
     xlab = "T° de l'air")



#Influence de Rainf.EOBS

sonde825 <- filter(base, id_sonde == 825)
sonde827 <- filter(base, id_sonde == 827)
sonde828 <- filter(base, id_sonde == 828)
sonde830 <- filter(base, id_sonde == 830)

baseSansPluie825 <- filter(sonde825, Rainf.EOBS == 0)
baseSansPluie827 <- filter(sonde827, Rainf.EOBS == 0)
baseSansPluie828 <- filter(sonde828, Rainf.EOBS == 0)
baseSansPluie830 <- filter(sonde830,Rainf.EOBS == 0)

baseAvecPluie825 <- filter(sonde825, Rainf.EOBS > 0)
baseAvecPluie827 <- filter(sonde827, Rainf.EOBS > 0)
baseAvecPluie828 <- filter(sonde828, Rainf.EOBS > 0)
baseAvecPluie830 <- filter(sonde830,Rainf.EOBS > 0)

mean(baseSansPluie825$Teau)
mean(baseAvecPluie825$Teau)

mean(baseSansPluie827$Teau)
mean(baseAvecPluie827$Teau)

mean(baseSansPluie828$Teau)
mean(baseAvecPluie828$Teau)

mean(baseSansPluie830$Teau)
mean(baseAvecPluie830$Teau)

baseSansPluie825$Teau825 <- baseSansPluie825$Teau #Renommage des colonnes Teau en fct de la sonde
baseSansPluie827$Teau827 <- baseSansPluie827$Teau
baseSansPluie828$Teau828 <- baseSansPluie828$Teau
baseSansPluie830$Teau830 <- baseSansPluie830$Teau

baseAvecPluie825$Teau825 <- baseAvecPluie825$Teau #Renommage des colonnes Teau en fct de la sonde
baseAvecPluie827$Teau827 <- baseAvecPluie827$Teau
baseAvecPluie828$Teau828 <- baseAvecPluie828$Teau
baseAvecPluie830$Teau830 <- baseAvecPluie830$Teau

AP825 <- aggregate(Teau825~date, data = baseAvecPluie825, FUN = mean, na.rm = T) #Moyenne Teau par date
AP827 <- aggregate(Teau827~date, data = baseAvecPluie827, FUN = mean, na.rm = T)
AP828 <- aggregate(Teau828~date, data = baseAvecPluie828, FUN = mean, na.rm = T)
AP830 <- aggregate(Teau830~date, data = baseAvecPluie830, FUN = mean, na.rm = T)

SP825 <- aggregate(Teau825~date, data = baseSansPluie825, FUN = mean, na.rm = T) #Moyenne Teau par date
SP827 <- aggregate(Teau827~date, data = baseSansPluie827, FUN = mean, na.rm = T)
SP828 <- aggregate(Teau828~date, data = baseSansPluie828, FUN = mean, na.rm = T)
SP830 <- aggregate(Teau830~date, data = baseSansPluie830, FUN = mean, na.rm = T)

par(mfrow=c(2,2))

boxplot(baseAvecPluie825[,c('Teau825')], baseSansPluie825[,c("Teau825")],
        names = c("Avec pluie", "Sans pluie"),
        col = c("blue", "yellow"),  
        main = paste("Température de l'eau mesuré par 825"),     
        ylab = "Température")      

boxplot(baseAvecPluie827[,c('Teau827')], baseSansPluie827[,c("Teau827")],
        names = c("Avec pluie", "Sans pluie"),
        col = c("blue", "yellow"),  
        main = paste("Température de l'eau mesuré par 827"),     
        ylab = "Température")   

boxplot(baseAvecPluie828[,c('Teau828')], baseSansPluie828[,c("Teau828")],
        names = c("Avec pluie", "Sans pluie"),
        col = c("blue", "yellow"),  
        main = paste("Température de l'eau mesuré par 828"),     
        ylab = "Température")   

boxplot(baseAvecPluie830[,c('Teau830')], baseSansPluie830[,c("Teau830")],
        names = c("Avec pluie", "Sans pluie"),
        col = c("blue", "yellow"),  
        main = paste("Température de l'eau mesuré par 830"),     
        ylab = "Température")   

