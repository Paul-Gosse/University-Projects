<?php
  class User {
    private $username;
    private $password;

    public function __construct($username,$password) {
      $this->username = $username;
      $this->password = password_hash($password, PASSWORD_BCRYPT);
    }

    public function getUsername() {
      return $this->username;
    }

    public function getPw() {
      return $this->password;
    }

  }
 ?>
