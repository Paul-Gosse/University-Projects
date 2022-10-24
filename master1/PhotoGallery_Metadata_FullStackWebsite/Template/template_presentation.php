<!DOCTYPE html>
<html lang="fr">

<head>
	<title>Galerie</title>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="css/style.css" />
</head>

<body>
	<nav role="navigation">
		<div id='menuToggle'>
			<input id="checkbox" type="checkbox" />
			<span></span>
			<span></span>
			<span></span>
			<ul id='menu'>
				<?php
				foreach ($menu as $text => $link) {
					echo "<li><a href=\"$link\">$text</a></li>";
				}
				?>
			</ul>
		</div>
	</nav>
	<br>
	<main>
		<h2>Page technique du projet</h2>
		<div id='texte'>
			<h3>Composition du groupe :</h3>
			<ul id='moi'>
				<li>Paul Gosse</li>
			</ul>
			<div>
				<h3>Implémentation :</h3>
				<h4>Page accueil</h4>
				<p>
					Pour cette page, il me fallait un élément avec l'image donc j'ai
					réalisé les classes dans le model permettant de créer un objet Galerie
					qui va avoir seulement un chemin menant à l'image en argument. Les
					classes utilisées sont Galerie, GalerieStorage(méthode read pour lire une image
					et readall pour toute) et GalerieStorageStub. J'ai donc aussi créé un contrôleur
					qui va permettre de créer un GalerieStorageStub que l'on va readAll pour avoir
					toutes les images, suite à ça j'injecte du html dans des variables les balises
					avec les images que je vais faire appel dans la vue pour que le template puisse l'utiliser.
				</p>
				<h4>Page Détail</h4>
				<p>
					Sur le même principe que la page accueil sauf que cette fois-ci je ne readAll
					pas mais j'utilise la méthode read($id) qui lit une seule image. ainsi dès lors
					que mon objet n'est pas vide je créer une variable qui va stocker le chemin de l'image
					puis ensuite l'utiliser pour l'afficher avec une balise img. Pour la map, j'ai utilisé
					l'api de google maps que j'ai directement écrit dans le html avec une balise script,
					car lorsque je mettais le script dans un fichier javascript celui-ci ne s'exécutait pas.
					Pour avoir la position de la photo, j'extrais seulement la latitude et la longitude avec
					les bons tags sur la commande exiftool. Lorsque les coordonnées GPS sont spécifiées la map
					se place à l'endroit de la photo sinon je l'ai fait se placer à Paris (à la Tour Eiffel).
					Ensuite j'ai décidé pour un souci de style de séparer tous les éléments en petite partie
					que l'on peut scroll donc j'ai dû exécuter un bon nombre de requêtes exiftool pour faire
					afficher toutes les données. J'ai une seconde partie d'extraction de données destinée aux
					balises meta pour les Microdata, les données Open Graph et Twitter Cards. Et tous les
					résultats sont stockés dans des variables envoyés à la view pour ensuite pouvoir les appeler dans le template.
				</p>
				<h4>Page Upload</h4>
				<p>
					Pour la page upload, j'ai décidé de laisser la page accessible mais lorsque vous n'êtes pas connectés
					un message vous indiquant de vous connecter apparaît. Dès lors que vous êtes connectés vous avez accès
					au formulaire qui est créé dans le contrôleur stocker dans des variables qui sont ensuite echo dans le
					template. À partir du moment ou le formulaire et submit un script Javascript s'exécute qui va créer un
					formData permettant de stocker les images postées ensuite en utilisant un XMLHttpRequest qui va donc envoyer
					les données au serveur de notre formData nous pouvons, dans le script Php donc notre contrôleur,
					récupérer notre fichier dans la variable $_FILES. À ce moment là, j'ai rencontré un problème qui était
					que je ne pouvais pas ajouter les images dans le constructeur de GalerieStorageStub pour les déclarer car
					le constructeur était statique. Donc j'ai réalisé un constructeur dynamique qui a chaque appel va parcourir
					le dossier images avec la fonction glob qui stocke chaque image dans un tableau. Et ainsi faire un foreach pour
					créer tous les éléments dans le dossier et donc avoir les images qui s'ajoutent et qui peuvent s'afficher dans
					la page accueil. Pour ce qui est de l'affichage de l'image dès l'upload, je l'ai donc réalisé en Javascript
					en créant une fonction qui va prendre en argument un fichier et retourner une promesse qui va créer un fileReader
					puis un element img dans le DOM auquel j'attribue un id, puis au load j'ajoute à la source de mon élement le résultat
					du fileReader qui va ensuite être lu grace à la méthode readAsDataURL puis je resolve mon objet précédemment créer.
					J'ai réalisé l'upload multiple de fichier avec une boucle for qui va parcourir mes fichiers pour les ajouter au FormData
					pour ainsi qu'il soit transférer au serveur et je vais appendChild mes résultats de ma fonction qui crée une image pour
					les ajouter au body pour qu'il s'affiche. Les images s'affichent dès l'upload.
				</p>
				<h4>Page Liste</h4>
				<p></p>
				<br>
				<h4>Page Connexion</h4>
				<p>
					Pour la page connexion, je me suis inspiré du Tp que nous avons réalisé avec une classe AuthenticationManager
					qui va donc avoir les méthodes pour connecter l'utilisateur si ces identifiants sont bons, pour déconnecter
					l'utilisateur et une dernière pour vérifier que l'utilisateur est connecté que nous utilisons pour les pages
					upload et liste. Ainsi notre contrôleur va tout d'abord vérifier que notre utilisateur est connecté pour envoyer
					le contenu correspondant au cas présent. Et faire les appels aux méthode de connexion ou déconnexion au moment choisi.
				</p>
			</div>
		</div>
	</main>
</body>

</html>