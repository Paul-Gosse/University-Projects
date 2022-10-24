<!DOCTYPE html>
<html lang="fr">

<head>
	<title>Galerie</title>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="css/style.css" />
	<script defer src="javascript/upload.js"></script>
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
		<div id='upload'>
			<div id='titre_upload'>
				<?php echo $title ?>
			</div>
			<div id='form_upload'>
				<?php echo $content ?>
			</div>
		</div>
	</main>
</body>

</html>