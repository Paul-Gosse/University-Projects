<?php

namespace GosseLerouge\Framework;

/**
 * Class Response
 *
 * embryon de classe pour gérer la réponse HTTP
 */
class Response
{
    /**
     * @var array
     * liste des en-têtes HTTP
     */
	private $headers = array();

    /**
     * @param $headerValue
     * ajouter un en-tête à la liste
     * par exemple pour changer le Content-Type
     */
	public function addHeader($headerValue) {
		$this->headers[] = $headerValue;
	}

    /**
     * envoie tous les headers au client
     */
	public function sendHeaders() {
		foreach ($this->headers as $header) {
			header($header);
		}
	}

    /**
     * @param $content
     * envoi de la réponse au client
     */
	public function send($content)
	{
		$this->sendHeaders();
		echo $content;
	}
}
