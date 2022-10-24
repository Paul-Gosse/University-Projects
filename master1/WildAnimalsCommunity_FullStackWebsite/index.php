<?php
set_include_path("./src");

session_name("AnimauxSauvagesGosseLerouge");
session_start();
/* on lance l'autoloader et on redirige vers le FrontController
 */

$myAutoloader = function($className) {
    $pathName = str_replace('\\', '/', $className);
    $fullPath = __DIR__."/src/".$pathName.'.php';

    include $fullPath;
};
spl_autoload_register($myAutoloader); //lance l'autoload pour nos propres classes
require_once 'vendor/autoload.php'; //lance l'autoload pour les fichiers du vendor

use GosseLerouge\Framework\Request;
use GosseLerouge\Framework\Response;
use GosseLerouge\Framework\Control\FrontController;



$request = new Request($_GET, $_POST, $_FILES, $_SERVER, $_SESSION);
$response = new Response();
$router = new FrontController($request, $response);
$router->execute();
