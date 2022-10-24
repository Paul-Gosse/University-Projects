<?php

namespace GosseLerouge\Framework\Control;

use GosseLerouge\Framework\AuthenticationManagerInterface;
use GosseLerouge\Framework\Request;
use GosseLerouge\Framework\Response;

class AbstractController
{
	protected Request $request;
	protected Response $response;
    protected AuthenticationManagerInterface $authenticationManager;
	
	public function __construct(Request $request, Response $response, AuthenticationManagerInterface $authenticationManager, \Twig\Environment $twig, \PDO $dbh)
	{
		$this->request = $request;
		$this->response = $response;
        $this->authenticationManager = $authenticationManager;
		$this->twig = $twig;
        $this->dbh = $dbh;
	}
	
	public function execute($action)
	{
		return $this->$action();
	}
	
	public function defaultAction()
	{
		return $this->showDB();
	}

	public function makeHomePage()
    {   
        return $this->twig->render('index.html.twig', [
            'title' => 'Accueil',
            'connectionInfo' => $this->getConnectionInfo(),
            'message' => $this->interpretMessage(),
            ]);
    }

    public function getConnectionInfo() : array
    {
        return $this->authenticationManager->getConnectionInfo();
    }

    public function interpretMessage() : array
    {
        $message = [];
	    if (null != ($this->request->getSession("message"))) {
            switch ($_SESSION["message"]){
                case 'disconnected':
                    $message["text"] = "Vous avez été déconnecté.";
                    $message["type"] = "validation";
                    break;
                case 'authentication-succeed':
                    $message["text"] = "Connexion réussie.";
                    $message["type"] = "validation";
                    break;
                case 'authentication-failed':
                    $message["text"] = "Échec de la connexion !";
                    $message["type"] = "warning";
                    break;
                case 'connection-required':
                    $message["text"] = "Veuillez vous connecter pour accéder à cette section.";
                    $message["type"] = "warning";
                    break;
                case 'modification-pswd-succeed':
                    $message["text"] = "Votre mot de passe a bien été modifié.";
                    $message["type"] = "validation";
                    break;
                case 'modification-failed':
                    $message["text"] = "La modification a échouée, veuillez réessayez.";
                    $message["type"] = "warning";
                    break;
                case 'creation-failed':
                    $message["text"] = "Le nom d'utilisateur est déjà pris !";
                    $message["type"] = "warning";
                    break;
                case 'uploaded-animal-success':
                    $message["text"] = "Votre animal a été ajouté à la carte.";
                    $message["type"] = "validation";
                    break;
                case 'uploaded-animal-failed':
                    $message["text"] = "Votre animal n'a pas pu être ajouté à la carte.";
                    $message["type"] = "warning";
                    break;
                case 'update-animal-failed':
                    $message["text"] = "La modification a échouée, veuillez réessayez.";
                    $message["type"] = "warning";
                    break;
                case 'update-animal-success':
                    $message["text"] = "L'animal a bien été modifié.";
                    $message["type"] = "validation";
                    break;
                case 'post-commentary-success':
                    $message["text"] = "Commentaire posté avec succès!";
                    $message["type"] = "validation";
                    break;
                case 'post-commentary-failed':
                    $message["text"] = "Echec de l'envoi du commentaire.";
                    $message["type"] = "warning";
                    break;
                default:
                    $message["text"] = $this->request->getSession("message");
                    $message["type"] = "warning";
            }
        } else {
            $message["text"] = "RAS";
        }
        $this->request->setSession("message", "RAS");
        return $message;
    }

    public function explodeNameOfFileFromPathURL($name)
    {
        $newStr = explode("/", $name);
        return $newStr[2];
    }

	public function show()
	{
	}

 }
