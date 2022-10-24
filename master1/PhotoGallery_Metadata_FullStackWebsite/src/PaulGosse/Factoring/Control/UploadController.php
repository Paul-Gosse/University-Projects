<?php


namespace src\PaulGosse\Factoring\Control;

use src\PaulGosse\Factoring\Control\Request;
use src\PaulGosse\Factoring\Control\Response;
use src\PaulGosse\Factoring\View\View;
use src\PaulGosse\Factoring\Control\AuthenticationManager;

class UploadController
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
            $this->response->addHeader('X-Debugging: show me upload page');
            $title = '<h3>Veuillez upload une image</h3>';
            $content = '
                    <form enctype="multipart/form-data" action="?o=upload&a=show" method="POST" id="myForm">
                        <input multiple type="file" accept=".png,.jpg,.jpeg" name="file[]" id="myFile">
                        <input type="submit" id="submit" name="upload" value="Envoyez">
                        <br>
                        <progress id="progressBar"></progress>
                    </form>';
            if (!empty($_FILES)) {
                foreach ($_FILES as $key) {
                    $tmpName = $key['tmp_name'];
                    $name = $key['name'];
                    if (move_uploaded_file($tmpName, 'images/' . $name)) {
                        echo 'VOUS AVEZ REUSSI À UPLOAD VOTRE IMAGE';
                    }
                }
            }
        } else {
            $title = "<h3>Veuillez vous connecter avant d'upload</h3>";
            $content = "<p> Vous n'avez pas accès à l'upload pour le moment</p>";
        }
        $this->view->setPart('title', $title);
        $this->view->setPart('content', $content);
    }
}
