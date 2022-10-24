<?php

namespace GosseLerouge\Framework;

use GosseLerouge\Framework\Request;

class Router
{
    protected $controllerClassName;
    protected $controllerAction;
    protected Request $request;

    public function __construct(Request $request)
    {
        $this->request = $request;
        $this->parseRequest();
    }

    public function getControllerClassName()
    {
        return $this->controllerClassName;
    }

    public function getControllerAction()
    {
        return $this->controllerAction;
    }

    protected function parseRequest()
    {
      // un nom de package est-il spécifié dans l'URL ?
        $package = $this->request->getGetParam('o');

        // Regarder quel contrôleur instancier
        switch ($package) 
        {
            case 'home':
                $this->controllerClassName = 'GosseLerouge\App\Image\Control\HomeController';
                break;
            case 'upload':
                $this->controllerClassName = 'GosseLerouge\App\Image\Control\UploadController';
                break;
            case 'connect':
                $this->controllerClassName = 'GosseLerouge\Framework\Control\ConnexionController';
                break;
            case 'codesniffer':
                $command = 'phpcs';
                exec($command,$data);
                var_dump($data);
                break;
            case 'PDO':
                $this->controllerClassName = 'GosseLerouge\App\Animaux\Control\MySQLOperations';
                break;
            case 'listEdit':
                $this->controllerClassName = 'GosseLerouge\App\Image\Control\ListEditController';
                break;
            case 'edit':
                $this->controllerClassName = 'GosseLerouge\App\Image\Control\EditController';
                break;
            case 'details':
                $this->controllerClassName = 'GosseLerouge\App\Image\Control\DetailsController';
                break;
            default:
                // idem ici, on peut imaginer un package à utiliser par défaut
                $this->controllerClassName = 'GosseLerouge\App\Animaux\Control\MySQLOperations';
        }

        // tester si la classe à instancier existe bien. Si non lancer une Exception.
        if (!class_exists($this->controllerClassName)) 
        {
            throw new \Exception("Classe {$this->controllerClassName} non existante");
        }

        // regarder si une action est demandée dans l'URL
        // si le paramètre 'a' n'existe pas alors l'action sera 'defaultAction'
        $this->controllerAction = $this->request->getGetParam('a', 'defaultAction');
	}
}
