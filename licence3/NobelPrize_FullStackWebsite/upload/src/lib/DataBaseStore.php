<?php

  class dataBaseStore {
    private $database;

    public function __construct() {
      $this->database = new PDO("mysql:host=addressToBase;port=3306;dbname=database;charset=utf8","username","password");
      }

    public function getDatabase() {
      return $this->database;
    }


    public function insertUser($user) {
      $stmt = $this->database->prepare("INSERT INTO login (username,password) VALUES (:username,:password);");
      $stmt->execute(['username'=>$user->getUsername(),
                      'password'=>$user->getPw()]);
      }

    public function insertPrice($price) {
      $stmt = $this->database->prepare("INSERT INTO prix (utilisateur,type,date_prix,pays,prenom,nom) VALUES
      (:utilisateur,:type,:date_prix,:pays,:prenom,:nom);");
      $stmt->execute(['utilisateur'=>$price->getUtilisateur(),
                      'type'=>$price->getType(),
                      'date_prix'=>$price->getDate(),
                      'pays'=>$price->getPays(),
                      'prenom'=>$price->getPrenom(),
                      'nom'=>$price->getNom()]
                    );
    }

    public function updatePrice($id){
      $stmt = $this->database->prepare("UPDATE prix SET type=:type,date_prix=:date_prix,pays=:pays,prenom=:prenom,nom=:nom WHERE id=:id");
      $stmt->execute(['id'=>$id,
                      'type'=>$_POST['choix_prix'],
                      'date_prix'=>$_POST['annee'],
                      'pays'=>$_POST['pays'],
                      'prenom'=>$_POST['prenom'],
                      'nom'=>$_POST['nom']]
                    );
    }

    public function delPrice($id){
      $stmt = $this->database->prepare("DELETE FROM prix WHERE id=:id" );
      $stmt->setFetchMode(PDO::FETCH_ASSOC);
      $stmt->execute(['id'=>$id]);
    }

    public function getPriceDB() {
      $stmt = $this->database->query("SELECT * FROM prix ORDER BY type,date_prix;");
      $stmt->setFetchMode(PDO::FETCH_ASSOC);
      $pricetab = $stmt->fetchAll();
      return $pricetab;
    }

    public function getPriceDBById($id) {
      $stmt = $this->database->prepare("SELECT * FROM prix WHERE id=:id");
      $stmt->setFetchMode(PDO::FETCH_ASSOC);
      $stmt->execute(['id'=>$id]);
      $pricetab = $stmt->fetch();
      return $pricetab;
    }

    public function getUserdb() {
      $stmt = $this->database->query("SELECT * FROM login ;");
      $stmt->setFetchMode(PDO::FETCH_ASSOC);
      $pricetab = $stmt->fetchAll();
      return $pricetab;
    }

    public function getPriceId(){
      $stmt = $this->database->query("SELECT id FROM prix ORDER BY id;");
      $stmt->setFetchMode(PDO::FETCH_ASSOC);
      $idtab = $stmt->fetchAll();
      $id = $idtab[count($idtab)-1]['id'];
      return $id;
    }

    public function getSortedPriceDB($sorttype) {
      $stmt = $this->database->query("SELECT * FROM prix ORDER BY ".$sorttype.";");
      $stmt->setFetchMode(PDO::FETCH_ASSOC);
      $pricetab = $stmt->fetchAll();
      return $pricetab;
    }

  }

  ?>
