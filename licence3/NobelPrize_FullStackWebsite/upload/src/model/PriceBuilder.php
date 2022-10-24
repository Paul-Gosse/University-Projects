<?php
  class PriceBuilder {
    private $data;
    private $error;

    public function __construct($data,$error = null) {
      $this->data = $data;
      $this->error = $error;
    }

    public function getData() { return $this->data; }

    public function getError() { return $this->error; }


  public function isValid() {
    if (empty($this->data['annee']) || empty($this->data['nom']) ||empty($this->data['prenom'])||empty($this->data['pays'])||empty($this->data['choix_prix'])) {
      $this->error = "Merci de remplir tous les champs";
      return false;
    }
    if (!is_numeric($this->data['annee']) || strlen($this->data['annee']) < 4 || strlen($this->data['annee']) > 4) {
      $this->error = "Format de la date incorrect";
      return false;
    }
    return true;
  }




  public function createPrice(){
  return new Price($_SESSION['utilisateur'],$this->data['choix_prix'],$this->data['annee'],$this->data['pays'],$this->data['prenom'],$this->data['nom']);
  }
}
 ?>
