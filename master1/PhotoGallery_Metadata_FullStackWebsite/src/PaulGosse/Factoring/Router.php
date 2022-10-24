<?php
namespace src\PaulGosse\Factoring;

use src\PaulGosse\Factoring\Control\Request;


class Router
{

	protected $controllerClassName;
	protected $controllerAction;
	protected $request;

	function __construct(Request $request)
	{
		$this->request=$request;
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

	public function parseRequest()
	{
		$package=$this->request->getGET('o');
		switch ($package){
			case 'galerie':
				$this->controllerClassName='src\PaulGosse\Factoring\Control\GalerieController';
				break;
			case 'connexion':
				$this->controllerClassName='src\PaulGosse\Factoring\Control\ConnexionController';
				break;
			case 'detail':
				$this->controllerClassName='src\PaulGosse\Factoring\Control\DetailController';
				break;
			case 'upload':
				$this->controllerClassName='src\PaulGosse\Factoring\Control\UploadController';
				break;
			case 'liste':
				$this->controllerClassName='src\PaulGosse\Factoring\Control\ListeController';
				break;
			case 'liste_modif':
				$this->controllerClassName='src\PaulGosse\Factoring\Control\ListeModifController';
				break;
			default:
				$this->controllerClassName='src\PaulGosse\Factoring\Control\GalerieController';
		}

		if(!class_exists($this->controllerClassName))
		{
			//throw new Exception ("Classe {$this->controllerClassName} non existante");
		}
		$this->controllerAction=$this->request->getGET('a', 'defaultAction');
	}
}
