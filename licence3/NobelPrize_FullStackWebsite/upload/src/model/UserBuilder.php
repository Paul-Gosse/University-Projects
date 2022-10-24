<?php
  class UserBuilder {
    private $data;
    private $error;

    public function __construct($data,$error = null) {
      $this->data = $data;
      $this->error = $error;
    }

    public function getData() { return $this->data; }

    public function getError() { return $this->error; }

    public function isValid() {


      if (empty($this->data['username']) || empty($this->data['password'])){
        $this->error= "Veuillez renseigner tous les champs";
        return false;
      }
      if (((strlen($this->data['username']) < 4 || strlen($this->data['password']) < 4 )) && (!empty($this->data['username']) || !empty($this->data['password']))) {
        $this->error = "Mot de passe ou nom d'utilisateur trop court il doit faire au minimum 4 caractères";
        return false;
      }


      return true;

    }

    public function createUser() {
      return new User($this->data['username'],$this->data['password']);

    }

  }
 ?>
