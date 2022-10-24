library(FactoMineR)
library(factoextra)
library(corrplot)
library(dplyr)

base <- read.table("~/Desktop/Master 1/Semestre2/Analyse/projet/base.csv", TRUE, sep = ";", quote="\"", 
                   na.strings = TRUE, strip.white = TRUE, dec= ",",
                   comment.char = "$",blank.lines.skip = TRUE)
base


#ACP sur la sonde 825
#Même méthode pour les trois autres sondes
head(base)

"Reduction de la table pour n'avoir que les individus en fonction des variables actives"
base.active825 <- select(filter(base, id_sonde == 825), Teau, Rainf.EOBS, Tair.EOBS)
base.active827 <- select(filter(base, id_sonde == 827), Teau, Rainf.EOBS, Tair.EOBS)
base.active828 <- select(filter(base, id_sonde == 828), Teau, Rainf.EOBS, Tair.EOBS)
base.active830 <- select(filter(base, id_sonde == 830), Teau, Rainf.EOBS, Tair.EOBS)


"Calcul de l'acp"
res.pca825 <- PCA(base.active825, graph = FALSE)
res.pca827 <- PCA(base.active827, graph = FALSE)
res.pca828 <- PCA(base.active828, graph = FALSE)
res.pca830 <- PCA(base.active830, graph = FALSE)


"Calcul des valeurs propres"
eig.val825 <- get_eigenvalue(res.pca825)
eig.val827 <- get_eigenvalue(res.pca827)
eig.val828 <- get_eigenvalue(res.pca828)
eig.val830 <- get_eigenvalue(res.pca830)

"Graphique des valeurs propres pour déterminer le nombre de composantes principales"
fviz_eig(res.pca825, addlabels = TRUE, ylim = c(0,75))
fviz_eig(res.pca827, addlabels = TRUE, ylim = c(0,75))
fviz_eig(res.pca828, addlabels = TRUE, ylim = c(0,75))
fviz_eig(res.pca830, addlabels = TRUE, ylim = c(0,75))
"97.4% des informations (variances) contenues dans les données sont conservées par les deux premiÃ¨res composantes principales"


"Eléments contenant tous les résultats pour les variables actives (coordonnées, corrélation entre variables et les axes, cosinus-carré et contributions)"
var825 <- get_pca_var(res.pca825)
var827 <- get_pca_var(res.pca827)
var828 <- get_pca_var(res.pca828)
var830 <- get_pca_var(res.pca830)

"Coordonnées"
head(var$coord)
"Corrélation"
head(var$cor)
"Cos2: qualité de répresentation"
head(var$cos2)
"Contributions aux composantes principales"
head(var$contrib)


"Cercle de corrélation"
"Graphique de corrélation des variables"
fviz_pca_var(res.pca825, col.var = "blue")
fviz_pca_var(res.pca827, col.var = "blue")
fviz_pca_var(res.pca828, col.var = "blue")
fviz_pca_var(res.pca830, col.var = "blue")

"Qualité de représentation"
corrplot(var825$cos2, is.corr=FALSE, col = colorRampPalette(c("red","white","blue"))(100))
corrplot(var827$cos2, is.corr=FALSE, col = colorRampPalette(c("red","white","blue"))(100))
corrplot(var828$cos2, is.corr=FALSE, col = colorRampPalette(c("red","white","blue"))(100))
corrplot(var830$cos2, is.corr=FALSE, col = colorRampPalette(c("red","white","blue"))(100))

"Cos2 total des variables sur Dim.1 et Dim.2"
fviz_cos2(res.pca825, choice = "var", axes = 1:2)
fviz_cos2(res.pca827, choice = "var", axes = 1:2)
fviz_cos2(res.pca828, choice = "var", axes = 1:2)
fviz_cos2(res.pca830, choice = "var", axes = 1:2)
"Cos2 total des variables sur Dim.1 et Dim.3"
fviz_cos2(res.pca825, choice = "var", axes = c(1,3))
fviz_cos2(res.pca827, choice = "var", axes = c(1,3))
fviz_cos2(res.pca828, choice = "var", axes = c(1,3))
fviz_cos2(res.pca830, choice = "var", axes = c(1,3))
"Cos2 total des variables sur Dim.2 et Dim.3"
fviz_cos2(res.pca825, choice = "var", axes = 2:3)
fviz_cos2(res.pca827, choice = "var", axes = 2:3)
fviz_cos2(res.pca828, choice = "var", axes = 2:3)
fviz_cos2(res.pca830, choice = "var", axes = 2:3)
"Colorer en fonction du cos2: qualité de représentation"
fviz_pca_var(res.pca825, col.var = "cos2",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07"),
             repel = TRUE)
fviz_pca_var(res.pca827, col.var = "cos2",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07"),
             repel = TRUE)
fviz_pca_var(res.pca828, col.var = "cos2",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07"),
             repel = TRUE)
fviz_pca_var(res.pca830, col.var = "cos2",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07"),
             repel = TRUE)


"Contributions des variables aux axes principaux"
corrplot(var825$contrib, is.corr=FALSE, col = colorRampPalette(c("white","lightblue","blue"))(100))
corrplot(var827$contrib, is.corr=FALSE, col = colorRampPalette(c("white","lightblue","blue"))(100))
corrplot(var828$contrib, is.corr=FALSE, col = colorRampPalette(c("white","lightblue","blue"))(100))
corrplot(var830$contrib, is.corr=FALSE, col = colorRampPalette(c("white","lightblue","blue"))(100))

"Contributions des variables Ã  PC1=Dim1"
fviz_contrib(res.pca825, choice = "var", axes = 1)
fviz_contrib(res.pca827, choice = "var", axes = 1)
fviz_contrib(res.pca828, choice = "var", axes = 1)
fviz_contrib(res.pca830, choice = "var", axes = 1)
"Contributions des variables Ã  PC2=Dim2"
fviz_contrib(res.pca825, choice = "var", axes = 2)
fviz_contrib(res.pca827, choice = "var", axes = 2)
fviz_contrib(res.pca828, choice = "var", axes = 2)
fviz_contrib(res.pca830, choice = "var", axes = 2)
"Contributions des variables Ã  PC3=Dim3"
fviz_contrib(res.pca825, choice = "var", axes = 3)
fviz_contrib(res.pca827, choice = "var", axes = 3)
fviz_contrib(res.pca828, choice = "var", axes = 3)
fviz_contrib(res.pca830, choice = "var", axes = 3)
"Contribution totale Ã  PC1 et PC2"
fviz_contrib(res.pca825, choice = "var", axes = 1:2)
fviz_contrib(res.pca827, choice = "var", axes = 1:2)
fviz_contrib(res.pca828, choice = "var", axes = 1:2)
fviz_contrib(res.pca830, choice = "var", axes = 1:2)
"Contribution totale Ã  PC1 et PC2"
fviz_contrib(res.pca825, choice = "var", axes = c(1,3))
fviz_contrib(res.pca827, choice = "var", axes = c(1,3))
fviz_contrib(res.pca828, choice = "var", axes = c(1,3))
fviz_contrib(res.pca830, choice = "var", axes = c(1,3))
"Contribution totale Ã  PC1 et PC2"
fviz_contrib(res.pca825, choice = "var", axes = 2:3)
fviz_contrib(res.pca827, choice = "var", axes = 2:3)
fviz_contrib(res.pca828, choice = "var", axes = 2:3)
fviz_contrib(res.pca830, choice = "var", axes = 2:3)
"Ligne en pointillé rouge indique la contribution moyenne attendue"
"Colorer en fonction du contrib: contribution des variables"
fviz_pca_var(res.pca825, col.var = "contrib",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07")
)
fviz_pca_var(res.pca827, col.var = "contrib",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07")
)
fviz_pca_var(res.pca828, col.var = "contrib",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07")
)
fviz_pca_var(res.pca830, col.var = "contrib",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07")
)
