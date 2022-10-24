<?php

namespace src\PaulGosse\Factoring\View;

class View
{

	protected $parts;
	protected $template;

	function __construct($template, $parts = array())
	{
		$this->template = $template;
		$this->parts = $parts;
	}

	public function setPart($key, $content)
	{
		$this->parts[$key] = $content;
	}

	public function getPart($key)
	{
		if (isset($this->parts[$key])) {
			return $this->parts[$key];
		} else {
			return null;
		}
	}

	public function render()
	{
		$menu = $this->getPart('menu');
		$title = $this->getPart('title');
		$content = $this->getPart('content');
		$image = $this->getPart('image');
		$error = $this->getPart('error');
		$button_edit = $this->getPart('button_edit');
		$button_remove = $this->getPart('button_remove');
		$meta = $this->getPart('meta');


		//extraction de données
		$exiftool = $this->getPart('exiftool');
		$file = $this->getPart('file');
		$jfif = $this->getPart('jfif');
		$iptc = $this->getPart('iptc');
		$photoshop = $this->getPart('photoshop');
		$icc_pro = $this->getPart('icc_pro');
		$icc_view = $this->getPart('icc_view');
		$icc_meas = $this->getPart('icc_meas');
		$ifd = $this->getPart('ifd');
		$exififd = $this->getPart('exififd');
		$gps = $this->getPart('gps');
		$xmpx = $this->getPart('xmp-x');
		$xmpxmp = $this->getPart('xmp-xmp');
		$xmpphoto = $this->getPart('xmp-photo');
		$xmpright = $this->getPart('xmp-right');
		$compo = $this->getPart('compo');
		$source = $this->getPart('source');
		$type = $this->getPart('type');
		$name = $this->getPart('name');
		$desc = $this->getPart('desc');
		$artist = $this->getPart('artist');
		$latitude = $this->getPart('latitude');
		$longitude = $this->getPart('longitude');





		ob_start();
		include($this->template);
		$data = ob_get_contents();
		ob_end_clean();
		return $data;
	}
}
