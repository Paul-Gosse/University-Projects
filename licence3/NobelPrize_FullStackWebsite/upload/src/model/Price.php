<?php
 class Price {
   private $utilisateur;
   private $type;
   private $date;
   private $pays;
   private $prenom;
   private $nom;

   public function __construct($utilisateur,$type,$date,$pays,$prenom,$nom) {
     $this->utilisateur = $utilisateur;
     $this->type = $type;
     $this->date = $date;
     $this->pays = $pays;
     $this->prenom = $prenom;
     $this->nom = $nom;
   }


   public function getUtilisateur() {
     return $this->utilisateur;
   }
   public function getType() {
     return $this->type;
   }

   public function getDate() {
     return $this->date;
   }

   public function getPays() {
     return $this->pays;
   }

   public function getPrenom() {
     return $this->prenom;
   }
   public function getNom() {
     return $this->nom;
   }


 }

 ?>
