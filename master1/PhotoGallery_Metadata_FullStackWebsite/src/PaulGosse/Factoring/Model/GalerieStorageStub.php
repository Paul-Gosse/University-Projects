<?php

namespace src\PaulGosse\Factoring\Model;

use src\PaulGosse\Factoring\Model\Galerie;
use src\PaulGosse\Factoring\Model\GalerieStorage;


class GalerieStorageStub implements GalerieStorage
{
  protected $galeriesStub;

  public function __construct()
  {
    $tab_images = glob('images/*.{jpg,png,jpeg}', GLOB_BRACE);

    $this->galeriesStub = array();

    foreach ($tab_images as $key => $value) {
      $this->galeriesStub[$key] = new Galerie($value);
    }
  }

  public function read($id)
  {
    if (key_exists($id, $this->galeriesStub)) {
      return $this->galeriesStub[$id];
    }
    return null;
  }

  public function readAll()
  {
    return $this->galeriesStub;
  }
}
