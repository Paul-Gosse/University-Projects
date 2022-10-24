<!DOCTYPE html>
<html lang="fr">

<head>
	<title>Galerie</title>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="css/style.css" />

	<meta property="og:title" content=<?php if (!empty($title)) {
											echo $title;
										} else {
											echo "Il n'y a pas de titre";
										} ?> />
	<meta property="og:site_name" content=<?php if (!empty($name)) {
												echo $name;
											} else {
												echo "Il n'y a pas de nom de site";
											} ?> />
	<meta property="og:type" content=<?php if (!empty($type)) {
											echo $type;
										} else {
											echo "Il n'y a pas de type";
										} ?> />
	<meta property="og:url" content='' />
	<meta property="og:description" content=<?php if (!empty($desc)) {
												echo $desc;
											} else {
												echo "Il n'y a pas de description";
											} ?> />
	<meta property="og:image" content=<?php if (!empty($source)) {
											echo $source;
										} else {
											echo "Il n'y a pas d'image";
										} ?> />

	<meta name="twitter:site" content=<?php if (!empty($name)) {
											echo $name;
										} else {
											echo "Il n'y a pas de nom de site";
										} ?> />
	<meta name="twitter:image" content=<?php if (!empty($source)) {
											echo $source;
										} else {
											echo "Il n'y a pas d'image";
										} ?> />
	<meta name="twitter:description" content=<?php if (!empty($desc)) {
													echo $desc;
												} else {
													echo "Il n'y a pas de description";
												} ?> />
	<meta name="twitter:creator" content=<?php if (!empty($artist)) {
												echo $artist;
											} else {
												echo "Il n'y a pas d'artiste";
											} ?> />
	<meta name="twitter:title" content=<?php if (!empty($title)) {
											echo $title;
										} else {
											echo "Il n'y a pas de titre";
										} ?> />
	<meta name="twitter:url" content='' />
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
		<div class='parent' id='tite_image_map' itemscope>
			<div id='title' itemprop="title">
				<?php
				if (!empty($title)) {
					echo "<h1>" . $title . "</h1>";
				} else {
					echo "<h1>Il n'y a pas de titre</h1>";
				}
				?>
			</div>
			<div class='child' id='titre_image' itemscope>
				<div id='image-solo' itemprop="image">
					<?php
					if (!empty($content)) {
						echo $content;
					} else {
						echo "Il n'y a pas d'image";
					}
					?>
				</div>
			</div>
			<div class='child' id='map'></div>
			<script>
				function initMap() {
					<?php
					if ($latitude && $longitude != null || $latitude && $longitude != '') {
						echo
						"const optionstrouvees = {
							lat: " . $latitude . ",
							lng: " . $longitude . "
						};

						const map = new google.maps.Map(document.getElementById('map'), {
							zoom: 6,
							center: optionstrouvees,
						});

						const marker = new google.maps.Marker({
							position: optionstrouvees,
							map: map,
						});";
					} else {
						echo
						"const optionsnontrouvees = {
							lat: 48.858370,
							lng: 2.294481
						};

						const map = new google.maps.Map(document.getElementById('map'), {
							zoom: 6,
							center: optionsnontrouvees,
						});

						const marker = new google.maps.Marker({
							position: optionsnontrouvees,
							map: map,
						});";
					}
					?>
				}
			</script>
			<script src="https://maps.googleapis.com/maps/api/js?key=your_keycallback=initMap&v=weekly" async></script>
			<p><?php if ($latitude == null || $longitude == null || $latitude == '' || $longitude == '') {
					echo "Nous n'avons pas obtenu les coordonnées GPS donc nous vous emmenons vers la plus belle ville du monde.";
				} ?></p>

		</div>
		<br>
		<h2>Métadonnées extraites</h2>
		<br>
		<div id='data' itemscope>
			<div id='meta' itemscope>
				<h3 itemprop="name">Exiftool</h3>
				<br>
				<?php
				if (!empty($exiftool)) {
					foreach ($exiftool as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées Exiftool";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">File</h3>
				<?php
				if (!empty($file)) {
					foreach ($file as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées File";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">JFIF</h3>
				<br>
				<?php
				if (!empty($jfif)) {
					foreach ($jfif as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées JFIF";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">IPTC</h3>
				<?php
				if (!empty($iptc)) {
					foreach ($iptc as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées IPTC";
				}
				?>
			</div>
			<div id='meta' itemscope>
				<h3 itemprop="name">Photoshop</h3>
				<br>
				<?php
				if (!empty($photoshop)) {
					foreach ($photoshop as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées Photoshop";
				}
				?>

			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">ICC_Profile</h3>
				<?php
				if (!empty($icc_pro)) {
					foreach ($icc_pro as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées ICC_Profile";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">ICC-view</h3>
				<?php
				if (!empty($icc_view)) {
					foreach ($icc_view as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées ICC-view";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">ICC-meas</h3>
				<?php
				if (!empty($icc_meas)) {
					foreach ($icc_meas as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées ICC-meas";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">IFD0</h3>
				<?php
				if (!empty($ifd)) {
					foreach ($ifd as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées IFD0";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">ExifIFD</h3>
				<?php
				if (!empty($exififd)) {
					foreach ($exififd as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées ExifFD";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">GPS</h3>
				<br>
				<?php
				if (!empty($gps)) {
					foreach ($gps as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées GPS";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">XMP-x</h3>
				<br>
				<?php
				if (!empty($xmpx)) {
					foreach ($xmpx as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées XMP-x";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">XMP-xmp</h3>
				<br>
				<?php
				if (!empty($xmpxmp)) {
					foreach ($xmpxmp as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées XMP-xmp";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">XMP-photoshop</h3>
				<?php
				if (!empty($xmpphoto)) {
					foreach ($xmpphoto as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées XMP-photoshop";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">XMP-xmpRights</h3>
				<br>
				<?php
				if (!empty($xmpright)) {
					foreach ($xmpright as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées XMP-xmpRights";
				}
				?>
			</div>

			<div id='meta' itemscope>
				<h3 itemprop="name">Composite</h3>
				<?php
				if (!empty($compo)) {
					foreach ($compo as $key => $value) {
						echo $value . "<br>";
					}
				} else {
					echo "<br>" . "Il n'y a pas de métadonnées Composite";
				}

				?>
			</div>
		</div>
	</main>
</body>

</html>