<?php
namespace src\PaulGosse\Factoring\Control;

class Request
{
  private $get;
  private $post;
  private $files;
  private $server;
  private $session;

  public function __construct($get, $post, $files, $server, $session)
  {
    $this->get=$get;
    $this->post=$post;
    $this->files=$files;
    $this->server=$server;
    $this->session=$session;
  }

  public function isRequest()
  {
    return (!empty($this->server['HTTP_X_REQUESTED_WITH'])&& strtolower($this->server['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest');
  }

  public function getGET($key, $defaut = null)
  {
    if(!isset($this->get[$key]))
    {
      return $defaut;
    }
    return $this->get[$key];
  }

  public function getPOST($key, $defaut = null)
  {
    if(!isset($this->post[$key]))
    {
      return $defaut;
    }
    return $this->post[$key];
  }

  public function getAllPOST()
  {
    return $this->post;
  }

  public function getSession($key)
  {
    return key_exists($key, $this->session)?$this->session[$key]:null;
  }

  public function setSession($cle, $valeur){
    $this->session[$cle] = $valeur;
  }

  public function __destruct()
  {
    $_SESSION = $this->session;
  }
}
