<?php
namespace src\PaulGosse\Factoring\Control;

class Response
{

  private $headers = array();

  public function addHeader($headerValue)
  {
    $this->headers[]=$headerValue;
  }

  public function sendHeaders()
  {
    foreach($this->headers as $header)
    {
      header($header);
    }
  }

  public function send($content)
  {
    $this->sendHeaders();
    echo $content;
  }

}
