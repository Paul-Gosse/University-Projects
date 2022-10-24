<?php

namespace GosseLerouge\Framework;

/**
 * embryon de classe Request.
 *
 * La classe ne gère et n'encapsule ici que les données GET, POST
 *
 * La classe ne gère pas non plus le cas où un .htaccess est utilisé pour faire
 * la réécriture d'URL.
 */

class Request
{
    private $get;
    private $post;
    private $files;
    private $server;
    private $session;

    public function __construct($get, $post, $files, $server, $session)
    {
        $this->get = $get;
        $this->post = $post;
        $this->files = $files;
        $this->server = $server;
        $this->session = $session;
    }

    /**
    * détection des requêtes AJAX
    */
    public function isAjaxRequest()
    {
    	return (!empty($this->server['HTTP_X_REQUESTED_WITH']) && strtolower($this->server['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest');
    }

    /**
     * @param $key la clé à chercher dans GET
     * @param $default la valeur à renvoyer si $key n'existe pas
     * @return null
     */
    public function getGetParam($key, $default = null)
    {
        if (!isset($this->get[$key])) {
            return $default;
        }
        return $this->get[$key];
    }

    /**
     * @param $key la clé à chercher dans POST
     * @param $default la valeur à renvoyer si $key n'existe pas
     * @return null
     */
    public function getPostParam($key, $default)
    {
        if (!isset($this->post[$key])) {
            return $default;
        }
        return $this->post[$key];
    }

    /**
     * obtenir tous les paramètres POST
     * @return array
     */
    public function getAllPostParams()
    {
        return $this->post;
    }

    public function getServer($key){
        return $this->server[$key];
    }

    public function getSession($key){
        return isset($this->session[$key]) ? $this->session[$key] : null;
    }

    public function setSession($key, $value){
        $this->session[$key] = $value;
    }

    public function  destroySession(){
        $_SESSION = $this->session; //on match les données
        session_destroy();
        session_name("AnimauxSauvagesGosseLerouge");
        session_start();
        $this->session = $_SESSION;
    }

    public function __destruct(){
      $this->setSession("previousURI", $this->getServer('REQUEST_URI'));
      $_SESSION = $this->session; //on match les données
    }
}
