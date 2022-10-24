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
		<div id='image'>
			<?php
			if ($image != null) {
				foreach ($image as $key => $value) {
					echo $image[$key];
				}
			} else {
				echo "<h3>Il n'y a pas d'image</h3>";
			}
			?>
		</div>
	</main>
</body>

</html>