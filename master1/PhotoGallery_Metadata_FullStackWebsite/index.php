<?php
// set_include_path("./src");
session_start();

function auto_chargement($class){

  $keys = explode("\\",$class);
  $filename = "";
  for ($i=0; $i <= count($keys)-2 ; $i++) { 
      $filename .= $keys[$i]."/";
  }
  $filename .= $keys[count($keys)-1];

  //echo $filename;
  include $filename.".php";
  

}

spl_autoload_register('auto_chargement');

use src\PaulGosse\Factoring\Control\FrontController;
use src\PaulGosse\Factoring\Control\Request;
use src\PaulGosse\Factoring\Control\Response;

$server=$_SERVER;
$request=new Request($_GET, $_POST, $_FILES, $server, $_SESSION);
$response=new Response();
$router=new FrontController($request, $response);
$router->execute();
