<?php

namespace src\PaulGosse\Factoring\Control;

use src\PaulGosse\Factoring\Router;
use src\PaulGosse\Factoring\View\View;
use src\PaulGosse\Factoring\Control\AuthenticationManager;


class FrontController
{

  protected $request;
  protected $response;

  function __construct($request, $response)
  {
    $this->request = $request;
    $this->response = $response;
  }

  public function execute()
  {
    if ($_GET == null or $_GET['o'] == "galerie") {
      $view = new View('Template/template_original.php');
    } elseif ($_GET['o'] == "connexion") {
      $view = new View('Template/template_connexion.php');
    } elseif ($_GET['o'] == "liste") {
      $view = new View('Template/template_liste.php');
    } elseif ($_GET['o'] == "presentation") {
      $view = new View('Template/template_presentation.php');
    } elseif ($_GET['o'] == "upload") {
      $view = new View('Template/template_upload.php');
    } elseif ($_GET['o'] == "detail") {
      $view = new View('Template/template_detail.php');
    } elseif ($_GET['o'] == "liste_modif") {
      $view = new View('Template/template_liste_modif.php');
    }


    $authentication = new AuthenticationManager($this->request);
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
      if (isset($_POST['utilisateur']) and isset($_POST['mdp'])) {
        if ($authentication->connecterUtilisateur($_POST["utilisateur"], $_POST["mdp"])) {
        } else {
          header('Location: index.php?o=connexion&a=show');
        }
      }
    }






    $router = new Router($this->request);
    $className = $router->getControllerClassName();
    $action = $router->getControllerAction();
    $controller = new $className($view, $this->request, $this->response, $authentication);
    $controller->execute($action);

    if ($this->request->isRequest()) {
      $content = $view->getPart('content');
    } else {
      $content = $view->render();
    }
    $this->response->send($content);
  }
}
