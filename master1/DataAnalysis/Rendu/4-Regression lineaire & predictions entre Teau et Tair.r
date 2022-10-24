library(ggplot2)
library(dplyr)
library(lubridate)
library(ggally)
library(fastICA)

base <- read.table("path-to/base.csv", TRUE, sep = ";", quote="\"", 
                   na.strings = TRUE, strip.white = TRUE, dec= ",",
                   comment.char = "$",blank.lines.skip = TRUE)

head(base)

base$date <- ymd(base$date)#permet de convertir en format date, année-mois-jour


#Regression linéaire simple

#Tests des différentes relations entre les variables
regression1 <- lm(Teau ~ Tair.EOBS, data = base)
regression2 <- lm(Teau ~ Rainf.EOBS, data = base)
regression3 <- lm(Tair.EOBS ~ Rainf.EOBS, data = base)

summary(regression1)
summary(regression2)
summary(regression3)


par(mfrow=c(1,1))
#Visualisation des différentes relations entre les variables
plot(Teau ~ Tair.EOBS, data=base, main="Regression linéaire entre Teau et Tair.EOBS")
abline(regression1, col = "red") 

plot(Teau ~ Rainf.EOBS, data=base)
abline(regression2, col = "blue") 

plot(Tair.EOBS ~ Rainf.EOBS, data=base)
abline(regression3, col = "green")

#On remarque une relation intéressante entre Teau et Tair.EOBS, les deux autres relations sont inexploitables.

#Lissage des valeurs
P <- lowess(base$Teau, base$Tair.EOBS, f = 0.33, iter = 1) #Lissage sur 33% des valeurs
P2 <- lowess(base$Teau, base$Tair.EOBS, f = 0.66, iter = 1) #Lissage sur 66% des valeurs

plot(Teau~Tair.EOBS, data = base)
plot(base$Teau, base$Tair.EOBS)
lines(P, col="magenta")
lines(P2, col="yellow")
legend("bottomright", legend=c("0.33", "0.66"),
       col=c("magenta", "yellow"), lty=1, cex=0.8)

df <- data.frame(base$Teau, base$Tair.EOBS, base$Rainf.EOBS)

library(corrplot)
corrplot(cor(df)) #Diagramme de corrélation


#PEARSON

cor.test(base$Teau, base$Tair.EOBS, method="pearson") # Cor = 0.922 donc corrélation positive
cor.test(base$Teau, base$Rainf.EOBS, method="pearson") # Cor = -0.03 donc variables indépendantes
cor.test(base$Tair.EOBS, base$Rainf.EOBS, method="pearson") #Cor = -0.04 donc variables indépendantes

#Calcul du R² pour l'ensemble des corrélations
cor(base$Teau, base$Tair.EOBS)^2 #R² à 0.85
cor(base$Teau, base$Rainf.EOBS)^2 #R² proche de 0
cor(base$Tair.EOBS, base$Rainf.EOBS)^2 #R² proche de 0

#Régression linéaire sur les variables corrélés (ie. les corrélations ou le r² > 0).

mylm=summary(lm(Teau~Tair.EOBS, data=base))
mylm

#P-value= 0, effet positif observé est le reflet d'un effet réel.
#Valeur de pente estimée à 0.57, la température à un effet positif non négligeable sur la température de l'eau
#R² de 0.8517, la quantité d'ajustement est donc très forte (On peut prédire la température de l'eau en fonction de celle de l'air)

plot(regression1, which = 1) #Relation vérifié et quasiment parfaite.

plot(regression1, which = 2) #Relations quartiles quasiment parfaite

hist(residuals(regression1),col="yellow",freq=F)

densite <- density(residuals(regression1)) #Estimation de la densité représenté par ces différentes valeurs

lines(densite, col = "red",lwd=3) # Superposer une ligne de densité à l'histogramme

#Les résidus suivent une loi normale

#Prédiction de la température avec une température de l'air témoin à 30 degrés
predict(regression1, newdata=data.frame(Tair.EOBS=30), se.fit=TRUE, interval = "prediction", level = 0.99)

#22.15 fitted, 18.716 lower, 25.60 upper

#Vérifions si la prédiction est fiable
base[base$Tair.EOBS == max(base$Tair.EOBS),] # 28.95 degrés pour Tair / entre 17.8 et 20.4 pour Teau
base[base$Teau == max(base$Teau),] # 22.968 degrés pour Teau / 27.46 pour Tair

#Prédictions avec comme base les t° max Air en fonction de la régression linéaire effectué.

predict(regression1, newdata=data.frame(Tair.EOBS=max(base$Tair.EOBS)), se.fit=TRUE, interval = "prediction", level = 0.99)

#Avec une intervalle de confiance de confiance à 99%, pour une T° de 28.95
#Fit = 21.55293  lower = 18.10968  upper = 24.99617

#On estime a 99% que la température de l'eau sera aux alentours des 21.5 degrès.
#Avec comme valeur la plus basse, 18.10 degrés et comme valeur la plus haute, 25 degrés.
#La prédiction est cohérente au vu des données obtenus (Variations de la température)

#Ne pas oublier que les températures de l'air recensées par EOBS ne sont qu'une moyenne de la journée.

#Graphique de prédiction sur 61 valeurs pour une Tair allant de 0 a 30 par intervalle de 0,5
new <- data.frame(Tair.EOBS = seq(0, 30, 0.5), Teau<-rep(mean(base$Teau),61))

yy <- predict(regression1, new, interval="prediction")

matplot(new$Tair.EOBS,yy,lty=c(1,3,3), type="l",ylab="Teau prédit" ,col=c('black','blue','blue'), main="Prédiction de la température de l'eau")
