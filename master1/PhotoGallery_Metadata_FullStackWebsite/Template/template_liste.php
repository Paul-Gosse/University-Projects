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
		<div id='modif'>
			<?php
			echo $title;
			?>
			<div id='image_button'>
				<div id='button'>
					<?php
					if ($content != null) {
						foreach ($content as $key => $value) {
							echo $value;
						}
					} else {
						echo "<p> Vous n'avez pas accès à la liste pour le moment</p>";
					}


					?>
				</div>
			</div>
		</div>
	</main>
</body>

</html>