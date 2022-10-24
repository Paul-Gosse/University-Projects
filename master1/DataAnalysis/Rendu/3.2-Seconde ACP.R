
library(FactoMineR)
library(factoextra)
library(corrplot)
library(dplyr)

base <- read.table("path-to/base.csv", TRUE, sep = ";", quote="\"", 
                   na.strings = TRUE, strip.white = TRUE, dec= ",",
                   comment.char = "$",blank.lines.skip = TRUE)
base


#ACP sur la sonde 825
#Même méthode pour les trois autres sondes
head(base)

"Reduction de la table pour n'avoir que les individus en fonction des variables actives"
base.active <- select(filter(base, id_sonde == 825), Teau, Rainf.EOBS, Tair.EOBS)
head(base.active)


"Calcul de l'acp"
res.pca <- PCA(base.active, graph = FALSE)
print(res.pca)


"Calcul des valeurs propres"
eig.val <- get_eigenvalue(res.pca)
eig.val
"Graphique des valeurs propres pour déterminer le nombre de composantes principales"
fviz_eig(res.pca, addlabels = TRUE, ylim = c(0,75))
"97.4% des informations (variances) contenues dans les données sont conservées par les deux premiÃ¨res composantes principales"


"Eléments contenant tous les résultats pour les variables actives (coordonnées, corrélation entre variables et les axes, cosinus-carré et contributions)"
var <- get_pca_var(res.pca)
var
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
fviz_pca_var(res.pca, col.var = "blue")


"Qualité de représentation"
corrplot(var$cos2, is.corr=FALSE, col = colorRampPalette(c("red","white","blue"))(100))
"Cos2 total des variables sur Dim.1 et Dim.2"
fviz_cos2(res.pca, choice = "var", axes = 1:2)
"Cos2 total des variables sur Dim.1 et Dim.3"
fviz_cos2(res.pca, choice = "var", axes = c(1,3))
"Cos2 total des variables sur Dim.2 et Dim.3"
fviz_cos2(res.pca, choice = "var", axes = 2:3)
"Colorer en fonction du cos2: qualité de représentation"
fviz_pca_var(res.pca, col.var = "cos2",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07"),
             repel = TRUE)


"Contributions des variables aux axes principaux"
corrplot(var$contrib, is.corr=FALSE, col = colorRampPalette(c("white","lightblue","blue"))(100))
"Contributions des variables Ã  PC1=Dim1"
fviz_contrib(res.pca, choice = "var", axes = 1)
"Contributions des variables Ã  PC2=Dim2"
fviz_contrib(res.pca, choice = "var", axes = 2)
"Contributions des variables Ã  PC3=Dim3"
fviz_contrib(res.pca, choice = "var", axes = 3)
"Contribution totale Ã  PC1 et PC2"
fviz_contrib(res.pca, choice = "var", axes = 1:2)
"Contribution totale Ã  PC1 et PC2"
fviz_contrib(res.pca, choice = "var", axes = c(1,3))
"Contribution totale Ã  PC1 et PC2"
fviz_contrib(res.pca, choice = "var", axes = 2:3)
"Ligne en pointillé rouge indique la contribution moyenne attendue"
"Colorer en fonction du contrib: contribution des variables"
fviz_pca_var(res.pca, col.var = "contrib",
             gradient.cols = c("#00AFBB", "#E7B800", "#FC4E07")
)
