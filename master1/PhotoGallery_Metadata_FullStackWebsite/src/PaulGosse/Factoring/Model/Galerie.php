<?php

namespace src\PaulGosse\Factoring\Model;

class Galerie
{
  protected $image;

  public function __construct($image)
  {
    $this->image = $image;
  }

  public function getImage()
  {
    return $this->image;
  }
}
