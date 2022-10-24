#Importation des donnees et gestion des formats====

base <- read.table("path-to/base.csv", TRUE, sep = ";", quote="\"", 
                   na.strings = TRUE, strip.white = TRUE, dec= ",",
                   comment.char = "$",blank.lines.skip = TRUE)
base$date=as.Date(base$date,format='%Y-%m-%d')
library('lubridate')
base$t=ymd_hms(base$t)#On peut acceder aux composantes avec year(),month(),day() ou hour()
base$id_sonde=as.factor(base$id_sonde)
attach(base)
#Statistiques descriptives

sonde=c('sonde 825','sonde 827','sonde 828','sonde 830')
annee=2013:2018
moyenne_Teau_825=aggregate(Teau[id_sonde==825],by=list(year(t[id_sonde==825])),mean)
moyenne_Teau_827=aggregate(Teau[id_sonde==827],by=list(year(t[id_sonde==827])),mean)
moyenne_Teau_828=aggregate(Teau[id_sonde==828],by=list(year(t[id_sonde==828])),mean)
moyenne_Teau_830=aggregate(Teau[id_sonde==830],by=list(year(t[id_sonde==830])),mean)
moyenne_Teau=rbind(moyenne_Teau_825$x,moyenne_Teau_827$x,moyenne_Teau_828$x,moyenne_Teau_830$x)
dimnames(moyenne_Teau)=list(sonde,annee)

moyenne_Tair_825=aggregate(Tair.EOBS[id_sonde==825],by=list(year(t[id_sonde==825])),mean)
moyenne_Tair_827=aggregate(Tair.EOBS[id_sonde==827],by=list(year(t[id_sonde==827])),mean)
moyenne_Tair_828=aggregate(Tair.EOBS[id_sonde==828],by=list(year(t[id_sonde==828])),mean)
moyenne_Tair_830=aggregate(Tair.EOBS[id_sonde==830],by=list(year(t[id_sonde==830])),mean)
moyenne_Tair=rbind(moyenne_Tair_825$x,moyenne_Tair_827$x,moyenne_Tair_828$x,moyenne_Tair_830$x)
dimnames(moyenne_Tair)=list(sonde,annee)

moyenne_Rainf_825=aggregate(Rainf.EOBS[id_sonde==825],by=list(year(t[id_sonde==825])),mean)
moyenne_Rainf_827=aggregate(Rainf.EOBS[id_sonde==827],by=list(year(t[id_sonde==827])),mean)
moyenne_Rainf_828=aggregate(Rainf.EOBS[id_sonde==828],by=list(year(t[id_sonde==828])),mean)
moyenne_Rainf_830=aggregate(Rainf.EOBS[id_sonde==830],by=list(year(t[id_sonde==830])),mean)
moyenne_Rainf=rbind(moyenne_Rainf_825$x,moyenne_Rainf_827$x,moyenne_Rainf_828$x,moyenne_Rainf_830$x)
dimnames(moyenne_Rainf)=list(sonde,annee)

ecart_type_Teau_825=aggregate(Teau[id_sonde==825],by=list(year(t[id_sonde==825])),sd)
ecart_type_Teau_827=aggregate(Teau[id_sonde==827],by=list(year(t[id_sonde==827])),sd)
ecart_type_Teau_828=aggregate(Teau[id_sonde==828],by=list(year(t[id_sonde==828])),sd)
ecart_type_Teau_830=aggregate(Teau[id_sonde==830],by=list(year(t[id_sonde==830])),sd)
ecart_type_Teau=rbind(ecart_type_Teau_825$x,ecart_type_Teau_827$x,ecart_type_Teau_828$x,ecart_type_Teau_830$x)
dimnames(ecart_type_Teau)=list(sonde,annee)

ecart_type_Tair_825=aggregate(Tair.EOBS[id_sonde==825],by=list(year(t[id_sonde==825])),sd)
ecart_type_Tair_827=aggregate(Tair.EOBS[id_sonde==827],by=list(year(t[id_sonde==827])),sd)
ecart_type_Tair_828=aggregate(Tair.EOBS[id_sonde==828],by=list(year(t[id_sonde==828])),sd)
ecart_type_Tair_830=aggregate(Tair.EOBS[id_sonde==830],by=list(year(t[id_sonde==830])),sd)
ecart_type_Tair=rbind(ecart_type_Tair_825$x,ecart_type_Tair_827$x,ecart_type_Tair_828$x,ecart_type_Tair_830$x)
dimnames(ecart_type_Tair)=list(sonde,annee)

ecart_type_Rainf_825=aggregate(Rainf.EOBS[id_sonde==825],by=list(year(t[id_sonde==825])),sd)
ecart_type_Rainf_827=aggregate(Rainf.EOBS[id_sonde==827],by=list(year(t[id_sonde==827])),sd)
ecart_type_Rainf_828=aggregate(Rainf.EOBS[id_sonde==828],by=list(year(t[id_sonde==828])),sd)
ecart_type_Rainf_830=aggregate(Rainf.EOBS[id_sonde==830],by=list(year(t[id_sonde==830])),sd)
ecart_type_Rainf=rbind(ecart_type_Rainf_825$x,ecart_type_Rainf_827$x,ecart_type_Rainf_828$x,ecart_type_Rainf_830$x)
dimnames(ecart_type_Rainf)=list(sonde,annee)

#Representation graphique====

#Comparaison des quatre sondes quant a la temperature de l'eau
par(mfrow=c(2,2))
plot(t[id_sonde==825],Teau[id_sonde==825],main='Sonde 825',xlab='Horaire',ylab='Temperature',type='l')
plot(t[id_sonde==827],Teau[id_sonde==827],main='Sonde 827',xlab='Horaire',ylab='Temperature',type='l')
plot(t[id_sonde==828],Teau[id_sonde==828],main='Sonde 828',xlab='Horaire',ylab='Temperature',type='l')
plot(t[id_sonde==830],Teau[id_sonde==830],main='Sonde 830',xlab='Horaire',ylab='Temperature',type='l')


#Il pourrait etre interessant de faire de la regression : expliquer la temperature de l'eau en fonction de celle de l'air pour chaque sonde

#Les temperatures d'eau sont relevees toutes les deux heures alors que les temperatures d'air une fois par jour.
#Calculons les moyennes journalieres de temperatures de l'eau.

TeauJourn825=aggregate(Teau[id_sonde==825],by=list(date[id_sonde==825]),mean)
Tair825=aggregate(Tair.EOBS[id_sonde==825],by=list(date[id_sonde==825]),mean)
rl825=lm(TeauJourn825$x~Tair825$x)
plot(Tair825$x,TeauJourn825$x,main='Teau en fonction de Tair, sonde 825',xlab='Teau',ylab='Tair')
abline(rl825$coefficients[1],rl825$coefficients[2],col='red')
cor(Tair825$x,TeauJourn825$x);cor(Tair825$x,TeauJourn825$x)^2#r=0.91, R2=0.83 lien lineaire fort

TeauJourn827=aggregate(Teau[id_sonde==827],by=list(date[id_sonde==827]),mean)
Tair827=aggregate(Tair.EOBS[id_sonde==827],by=list(date[id_sonde==827]),mean)
rl827=lm(TeauJourn827$x~Tair827$x)
plot(Tair827$x,TeauJourn827$x,main='Teau en fonction de Tair, sonde 827',xlab='Teau',ylab='Tair')
abline(rl827$coefficients[1],rl827$coefficients[2],col='red')
cor(Tair827$x,TeauJourn827$x);cor(Tair827$x,TeauJourn827$x)^2#r=0.96, R2=0.92 lien lineaire fort

TeauJourn828=aggregate(Teau[id_sonde==828],by=list(date[id_sonde==828]),mean)
Tair828=aggregate(Tair.EOBS[id_sonde==828],by=list(date[id_sonde==828]),mean)
rl828=lm(TeauJourn828$x~Tair828$x)
plot(Tair828$x,TeauJourn828$x,main='Teau en fonction de Tair, sonde 828',xlab='Teau',ylab='Tair')
abline(rl828$coefficients[1],rl828$coefficients[2],col='red')
cor(Tair828$x,TeauJourn828$x);cor(Tair828$x,TeauJourn828$x)^2#r=0.96, R2=0.91 lien lineaire fort

TeauJourn830=aggregate(Teau[id_sonde==830],by=list(date[id_sonde==830]),mean)
Tair830=aggregate(Tair.EOBS[id_sonde==830],by=list(date[id_sonde==830]),mean)
rl830=lm(TeauJourn830$x~Tair830$x)
plot(Tair830$x,TeauJourn830$x,main='Teau en fonction de Tair, sonde 830',xlab='Teau',ylab='Tair')
abline(rl830$coefficients[1],rl830$coefficients[2],col='red')
cor(Tair830$x,TeauJourn830$x);cor(Tair830$x,TeauJourn830$x)^2#r=0.93, R2=0.86 lien lineaire fort

#ACP en differenciant les donnees par sonde====
# install.packages('FactoMineR')
library('FactoMineR')
# install.packages('factoextra')
library('factoextra')

temps=1:23458
base825=data.frame(temps,Teau[id_sonde==825],Tair.EOBS[id_sonde==825],Rainf.EOBS[id_sonde==825])
names(base825)=c('temps','Teau','Tair','Rainf')
base827=data.frame(temps,Teau[id_sonde==827],Tair.EOBS[id_sonde==827],Rainf.EOBS[id_sonde==827])
names(base827)=c('temps','Teau','Tair','Rainf')
base828=data.frame(temps,Teau[id_sonde==828],Tair.EOBS[id_sonde==828],Rainf.EOBS[id_sonde==828])
names(base828)=c('temps','Teau','Tair','Rainf')
base830=data.frame(temps,Teau[id_sonde==830],Tair.EOBS[id_sonde==830],Rainf.EOBS[id_sonde==830])
names(base830)=c('temps','Teau','Tair','Rainf')
a=PCA(base825,ncp=3,axes=c(1,2));a2=PCA(base825,ncp=3,axes=c(1,3))
#Tair et Teau fortement correlees positivement. Rainf et temps indep de Tair et Teau
#Rainf et temps correlees negativement avec 1,2 mais positivement avec 1,3. Faible qualite de representation
b=PCA(base827,ncp=3,axes=c(1,2));b2=PCA(base827,ncp=3,axes=c(1,3))
c=PCA(base828,ncp=3,axes=c(1,2));c2=PCA(base828,ncp=3,axes=c(1,3))
d=PCA(base830,ncp=3,axes=c(1,2));d2=PCA(base830,ncp=3,axes=c(1,3))

#ACI====
# install.packages('fastICA')
library('fastICA')
#set.seed()
Teau_par_sonde=data.frame(Teau[id_sonde==825],Teau[id_sonde==827],Teau[id_sonde==828],Teau[id_sonde==830])
names(Teau_par_sonde)=c('s825','s827','s828','s830')
ica=fastICA(Teau_par_sonde,n.comp=2)
par(mfrow=c(2,1))
plot(t[id_sonde==825],ica$S[,1],type='l',main='1ere composante independante',xlab='Horaire',ylab='C1')
plot(t[id_sonde==825],ica$S[,2],type='l',main='2eme composante independante',xlab='Horaire',ylab='C2')
#C1 composante annuelle : temperature elevee en ete, froide en hiver.
#C2 dephasee par rapport a C1. Nappe phreatique ?

Tair_par_sonde=data.frame(Tair.EOBS[id_sonde==825],Tair.EOBS[id_sonde==827],Tair.EOBS[id_sonde==828],Tair.EOBS[id_sonde==830])
names(Tair_par_sonde)=c('s825','s827','s828','s830')
b=fastICA(Tair_par_sonde,n.comp=2)
plot(t[id_sonde==825],b$S[,1],type='l',main='1ere composante independante',xlab='Horaire',ylab='C1')#Pas exploitable
plot(t[id_sonde==825],b$S[,2],type='l',main='2eme composante independante',xlab='Horaire',ylab='C2')#Idem Teau :
#Temperatures froides en hiver et chaudes en ete.

#ACP avec les composantes extraites de l'ACI====
base825=data.frame(temps,Teau[id_sonde==825],Tair.EOBS[id_sonde==825],Rainf.EOBS[id_sonde==825],a$S[,1],a$S[,2])
names(base825)=c('temps','Teau','Tair','Rainf','C1','C2')
a=PCA(base825,ncp=3);a1=PCA(base825,ncp=3,axes=c(1,3))#C1 correlee positivement avec Teau et Tair

base825=data.frame(temps,Teau[id_sonde==825],Tair.EOBS[id_sonde==825],Rainf.EOBS[id_sonde==825],b$S[,1],b$S[,2])
names(base825)=c('temps','Teau','Tair','Rainf','C1','C2')
a=PCA(base825,ncp=3);a1=PCA(base825,ncp=3,axes=c(1,3))#C2 correlee positivement avec Teau et Tair
fviz_eig(a1,addlabels=TRUE,hjust=-0.3)

#Recuperation ACI du groupe

set.seed(800)# on met 1 pour initier le processus
a <- fastICA(Teau_par_sonde, 3, alg.typ = "parallel", fun = "logcosh", alpha = 1,
             method = "R", row.norm = FALSE, maxit = 200,
             tol = 0.0001, verbose = TRUE)

par(mfrow=c(3,1))
plot(t[id_sonde==825],a$S[,1],type='l')
plot(t[id_sonde==825],a$S[,2],type='l')
plot(t[id_sonde==825],a$S[,3],type='l')#Composante 3 basse en hiver, haute en ete.
C1=a$S[,1];C2=a$S[,2];C3=a$S[,3]
#ACP
base825=data.frame(temps,Teau[id_sonde==825],Tair.EOBS[id_sonde==825],Rainf.EOBS[id_sonde==825],C1,C2,C3)
names(base825)[2:4]=c('Teau','Tair','Rainf')
pca=PCA(base825,ncp=3)#Correlation positive entre C3 et Tair / Teau. Pas surprenant puisqu'on s'attend a des temperatures froides en hiver et chaudes en ete.
pca2=PCA(base825,ncp=3,axes=c(1,3))#Autres variables assez mal representees, pas de conclusion a tirer.

base827=data.frame(temps,Teau[id_sonde==827],Tair.EOBS[id_sonde==827],Rainf.EOBS[id_sonde==827],C1,C2,C3)
names(base827)[2:4]=c('Teau','Tair','Rainf')
pca=PCA(base827,ncp=3)
pca2=PCA(base827,ncp=3,axes=c(1,3))

base828=data.frame(temps,Teau[id_sonde==828],Tair.EOBS[id_sonde==828],Rainf.EOBS[id_sonde==828],C1,C2,C3)
names(base828)[2:4]=c('Teau','Tair','Rainf')
pca=PCA(base828,ncp=3)
pca2=PCA(base828,ncp=3,axes=c(1,3))
