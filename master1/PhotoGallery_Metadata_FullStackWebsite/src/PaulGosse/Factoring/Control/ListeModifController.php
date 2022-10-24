<?php

namespace src\PaulGosse\Factoring\Control;

use src\PaulGosse\Factoring\Model\GalerieStorageStub;
use src\PaulGosse\Factoring\Control\Request;
use src\PaulGosse\Factoring\Control\Response;
use src\PaulGosse\Factoring\View\View;
use src\PaulGosse\Factoring\Control\AuthenticationManager;


class ListeModifController
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
            $this->response->addHeader('X-Debugging: show me a image');
            $id = $this->request->getGet('id');
            $galerieStorage = new GalerieStorageStub();
            $galerie = $galerieStorage->read($id);
            if ($galerie !== null) {
                $image = $galerie->getImage();
                $title = "<h3>Modification des métadonnées d'une image</h3>";
                $content = "<figure>\n<img id='img_detail' src=\"$image\" alt=\"\" />\n";
                exec("exiftool " . $image,  $exiftool_all);
                $tab_result = array();
                foreach ($exiftool_all as $key => $value) {
                    $key_final = strstr($value, ':', true);
                    $key_final = strstr($key_final, '  ', true);
                    $value_final = substr($value, strpos($value, ":") + 1);
                    $tab_result[$key_final] = $value_final;
                }
                /* var_dump($tab_result); */
                $meta = "<form id='form_edit_exif' action ='?o=liste_modif&a=show&id=$id' method='POST'>";
                foreach ($tab_result as $key => $value) {
                    $meta .= "<label class='label_exif' for='$key'>$key</label>
                            <input class='change_exif' id='$key' type='text' name='$key' placeholder=" . $value . "><br>";
                }
                $meta .= "</form>";
                $meta .= "<button type='submit' form='form_edit_exif' value='Submit'>Enregistrer</button>";

                /* var_dump($_POST); */

                if (!empty($_POST) && !isset($_POST['edit'])) {
                    foreach ($_POST as $key => $value) {
                        if ($value != null) {
                            $tab_post[$key] = $value;
                        }
                    }
                    //var_dump($tab_post); 
                    foreach ($tab_post as $key => $value) {
                        $value = str_replace(" ", "", $value);
                        $key = str_replace("_", "", $key);
                        $key = str_replace(" ", "", $key);
                        exec("exiftool -" . $key . "=" . $value . "-overwrite_original " . $image);
                    }

                    $this->response->addHeader('Location: ?o=liste_modif&a=show&id=' . $id . '');
                    $this->response->sendHeaders();
                }
            }
        } else {
            $title = "<h3>Veuillez vous connecter avant de modifier ou supprimer</h3>";
            $content = "<p> Vous n'avez pas accès à la modification pour le moment</p>";
        }
        $this->view->setPart('meta', $meta);
        $this->view->setPart('title', $title);
        $this->view->setPart('content', $content);
    }
}
