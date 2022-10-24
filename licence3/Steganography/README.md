# Licence 3 - Steganography

| Year        | Place              |
| ----------- | ------------------ |
| 2020 - 2021 | University of Caen |

### Contexte /Context

Vous êtes un agent travaillant pour des renseignements (DGSE, mossad...).
Vos collègues effectuent des missions clandestines : ils doivent pouvoir communiquer entre continent sans être repéré.

You are an agent working for information (DGSE, mossad...).
Your colleagues carry out clandestine missions: they must be able to communicate between continents without being spotted.

### Objectif / Objective

Rechercher des moyens originaux (et donc probablement moins surveillés), non suspect par rapport à une activité normale, pour envoyer et peut-être même recevoir des messages / informations. On pourra se baser sur des protocoles ou applications existantes, chiffrés de base, et y ajouter des extensions / fonctionnalités / protocoles personnalisés proches cachés pour ne pas mettre en danger le clandestin.

Look for original (and therefore probably less guarded), non-suspicious ways compared to normal activity, to send and maybe even receive messages/information. We can base ourselves on existing protocols or applications, basic encrypted, and add extensions / functionalities / personalized protocols close hidden so as not to endanger the clandestine.

## Instructions d'éxécution / Execution instructions

Pour cacher une image dans une image:

- ouvrir steg_image.ipynb avec le notebook JupyterLab
- exécuter toutes les cellules afin de faire apparaître l'outil de sélection de fichier
- choisir les images (jpg ou png)
- exécuter la cellule suivante (numerotée 181)
- executer la dernière cellule

To hide an image in another one:

- open steg_image.ipynb with the notebook JupyterLab
- run all cells in order to bring up the file selection tool
- choose the images (jpg or png only)
- run the next cell (number 181)
- run the last cell

Pour cacher un texte dans une image:

- ouvrir steg_texte.ipynb
- exécuter toute les cellules
- saisir le message à masquer après la dernière cellule de la zone prévue
- appuyer sur entrée

To hide text in an image:

- open steg_texte.ipynb
- run all cells
- enter the message to be hidden after the last cell in the area provided
- press Enter

## Resultat / Result

Avec steg_image.ipnyb:

- un affichage de l'image avec l'image cachée et l'image cachée seule
- un fichier newimg.png si il y eu conversion d'une image
- un fichier fusion.png correspondant à l'image avec l'image cachée
- un fichier defusion.png correspondant à la seule image cachée

With steg_image.ipnyb:

- a display of the image with the hidden image and the hidden image alone
- a newimg.png file if an image has been converted
- a fusion.png file corresponding to the image with the hidden image
- a defusion.png file corresponding to the only hidden image

Avec steg_texte.ipnyb:

- un affichage sur Jupyter

With steg_texte.ipnyb:

- a display on Jupyter

### Prérequis / Requirements

- Python
- Jupyter
