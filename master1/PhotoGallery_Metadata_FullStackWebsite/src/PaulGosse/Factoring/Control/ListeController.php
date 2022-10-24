<?php

namespace src\PaulGosse\Factoring\Control;

use src\PaulGosse\Factoring\Model\GalerieStorageStub;
use src\PaulGosse\Factoring\Control\Request;
use src\PaulGosse\Factoring\Control\Response;
use src\PaulGosse\Factoring\View\View;
use src\PaulGosse\Factoring\Control\AuthenticationManager;


class ListeController
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
        if ($this->authentication->estConnecte()) {
            $this->response->addHeader('X-Debugging: show me an galerie');
            $galerieStorage = new GalerieStorageStub();
            $galerie = $galerieStorage->readAll();
            $galerieArray = array();
            if ($galerie !== null) {
                foreach ($galerie as $key => $value) {
                    $title = "<h3>Vous pouvez modifier la galerie</h3>";
                    $content = "<div id='image_form_liste'>";
                    $content .= "<figure><img id='img_liste' src=\"" . $value->getImage() . "\"" . "></figure>";
                    $content .= "<form id='form_edit' action ='?o=liste_modif&a=show&id=$key' method='POST'>
                                    <button class='button_edit' id='button_edit' type='submit' name='edit' >Modifier</button>
                                    </form>";
                    $content .= "<form id='form_remove' action ='?o=liste&a=show' method='POST'>
                                 <input class='input_remove' id='input_remove' type='submit' name='remove" . $key . "' value='supprimer'>
                                 </form>";
                    $content .= "</div>";
                    array_push($galerieArray, $content);
                    if (!empty($_POST['remove' . $key]) && $_POST['remove' . $key] == 'supprimer') {
                        unlink($value->getImage());
                        unset($galerieArray[$key]);
                    }
                }
            } else {
                $content = "<h3>Il n'y a pas d'image</h3>";
            }
            $this->view->setPart('title', $title);
            $this->view->setPart('content', $galerieArray);
        } else {
            $title = "<h3>Veuillez vous connecter avant de modifier ou supprimer</h3>";
            $this->view->setPart('title', $title);
        }
    }
}
