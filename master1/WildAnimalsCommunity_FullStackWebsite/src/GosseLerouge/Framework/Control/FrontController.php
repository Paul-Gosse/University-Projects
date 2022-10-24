<?php

namespace GosseLerouge\Framework\Control;

use GosseLerouge\Framework\AuthenticationManager;
use GosseLerouge\Framework\Request;
use GosseLerouge\Framework\Response;
use GosseLerouge\Framework\Router;

class FrontController
{

	protected Request $request;
    protected Response $response;
    protected AuthenticationManager $authenticationManager;

    public function __construct($request, $response)
    {
        $this->request = $request;
        $this->response = $response;
        $this->authenticationManager = new AuthenticationManager($request);
    }

    public function execute()
    {
        // demander au Router la classe et l'action à exécuter
        $router = new Router($this->request);
        $className = $router->getControllerClassName();
        $action = $router->getControllerAction();
        
        //instancier twig pour le passer au controleur
        $loader = new \Twig\Loader\FilesystemLoader(getcwd().'/templates');
		    $twig = new \Twig\Environment($loader);

        //instancier db pour le passer au controller
        try {
            $dbh = new \PDO("mysql:host=".'url_database'."; dbname=".'database_name'."",  'login', 'password');
        } catch (PDOException $e) {
            echo 'Connexion échouée : ' . $e->getMessage();
        }

        // instancier le controleur de classe et exécuter l'action
        $controller = new $className($this->request, $this->response, $this->authenticationManager, $twig, $dbh);
        $content = $controller->execute($action); //on récupère dans $content le contenu html de la page (ce que retourne la méthode render() de twig)

        $this->response->send($content);
    }
}
