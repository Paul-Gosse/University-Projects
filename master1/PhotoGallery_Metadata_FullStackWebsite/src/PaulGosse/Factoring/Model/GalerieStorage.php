<?php

namespace src\PaulGosse\Factoring\Model;

interface GalerieStorage
{
  public function read($id);

  public function readAll();
}
