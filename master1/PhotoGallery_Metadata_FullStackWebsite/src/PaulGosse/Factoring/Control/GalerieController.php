<?php

namespace src\PaulGosse\Factoring\Control;

use src\PaulGosse\Factoring\Model\GalerieStorageStub;
use src\PaulGosse\Factoring\Control\Request;
use src\PaulGosse\Factoring\Control\Response;
use src\PaulGosse\Factoring\View\View;
use src\PaulGosse\Factoring\Control\AuthenticationManager;


class GalerieController
{

  protected $view;
  protected $request;
  protected $response;

  function __construct(View $view, Request $request, Response $response, AuthenticationManager $authentication)
  {
    $this->view = $view;
    $this->request = $request;
    $this->response = $response;
    $this->authentication = $authentication;

    $menu = array(
      "Accueil" => '?o=galerie&a=show',
      "Upload" => '?o=upload&a=show',
      "Liste" => '?o=liste&a=show',
      "Présentation" => '?o=presentation&a=show',
      "Connexion" => '?o=connexion&a=show',
    );
    $this->view->setPart('menu', $menu);
  }

  public function execute($action)
  {
    $this->$action();
  }

  public function defaultAction()
  {
    return $this->show();
  }

  public function show()
  {
    $this->response->addHeader('X-Debugging: show me an galerie');
    $galerieStorage = new GalerieStorageStub();
    $galerie = $galerieStorage->readAll();
    $galerieArray = array();
    foreach ($galerie as $key => $value) {
      if ($galerie !== null) {
        $content = "<figure><a href='?o=detail&a=show&id=$key'><img id='img_accueil' src=\"" . $value->getImage() . "\"" . "></a></figure>";
        array_push($galerieArray, $content);
      } else {
        $this->unknowngalerie();
      }
    }
    $this->view->setPart('image', $galerieArray);
  }

  public function unknowngalerie()
  {
    $title = "galerie inconnu";
    $content = "Choisir une image parmi la liste";
    $this->view->setPart('title', $title);
    $this->view->setPart('content', $content);
  }
}
