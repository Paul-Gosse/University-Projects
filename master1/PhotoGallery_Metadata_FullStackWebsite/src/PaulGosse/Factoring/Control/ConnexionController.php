<?php

namespace src\PaulGosse\Factoring\Control;

use src\PaulGosse\Factoring\Control\Request;
use src\PaulGosse\Factoring\Control\Response;
use src\PaulGosse\Factoring\Control\AuthenticationManager;
use src\PaulGosse\Factoring\View\View;

class ConnexionController
{
  protected $view;
  protected $request;
  protected $response;
  protected $authentication;

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
    if ($this->authentication->estConnecte()) {
      $title = "<h3>Vous êtes connecté</h3>";
      $content = "
      <form id='form_deco' action =" . $_SERVER['REQUEST_URI'] . " method='POST'>
        <button class='button_deco' id='button_co' type='submit' name='deco' >Déconnexion</button>
      </form>";
      if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['deco'])) {
        $this->authentication->deconnecterUtilisateur();
      }
    } else {
      $title = "<h3>Veuillez-vous connecter</h3>";
      $content = "
      <form id='form_co' action=" . $_SERVER['REQUEST_URI'] . " method='post'>
        <label for='input_co' class='label' id='label_id'>Identifiant</label>
        <br>
        <input id='input_co' class='input_id' input='text' name='utilisateur' placeholder='Votre identifiant...'>
        <br>
        <label for='input_co' class='label' id='label_mdp' >Mot de passe</label>
        <br>
        <input id='input_co' class='input_mdp' input='text' type='password' name='mdp' placeholder='Votre mot de passe...'>
        <br>
			  <button id='button_co' type='submit'>Connexion</button>
      </form>";
      $this->authentication->connecterUtilisateur();
    }
    $this->view->setPart('title', $title);
    $this->view->setPart('content', $content);
  }
}
