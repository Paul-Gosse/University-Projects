<?php

class View {

  private $title;
  private $content;
  private $router;

  public function __construct($router,$feedback) {
    $this->title = "";
    $this->content = "";
    $this->router = $router;
    $this->feedback = $feedback;
  }

  public function setContent($content) {
    $this->content.= $content;
  }

  public function render() {
    require_once "Squelette.php";
  }

public function makeNav() {
  $tamp = $this->content;
  $this->content = " <h1>Prix Nobel</h1> <nav>
  <ol class='listnav'>
  <li><a href='".$this->router->getHomePageURL()."' > Accueil </a></li>
  <li><a href='".$this->router->getSignInPageURL()."' > Se connecter </a></li>
  <li><a href='".$this->router->getInformationPageURL()."' > &Agrave; propos </a></li>";

  if (!empty($_SESSION) && key_exists('utilisateur',$_SESSION)) {
    $this->content.="<li><a href='".$this->router->getNewPricePage()."' > Nouveau prix</a></li>";
    $this->content.="<li><a href='".$this->router->getAddedPricesPage()."' > Prix ajoutés</a></li>";
  }
  $this->content.="</ol> </nav>".$tamp;
}

 public function makePriceListSorted($pricetab) {
    $this->title = "Accueil";
    $this->content.= "<ul class='l1'>";
    foreach ($pricetab as $key => $value) {
      $data = $value['type']." | ".$value['nom']." : ".$value['date_prix'];

      if (key_exists("username",$_SESSION)) {
        $this->content.= "<li><a href='".$this->router->getNobelPriceURLById($value['id'])."' >".$data."</a></li>";
      }
      else {
        $this->content.= "<li>".$data."</li>";
      }
    }
    $this->content.="</ul> <br/><br/>
                      <form action='".$this->router->getHomePageURL()."' method='POST'>
                        <label>Comment souhaitez vous trier les prix nobels ?
                          <select name='type_tri'>
                            <option value='date_prix'>date</option>
                            <option value='type'>type</option>
                            <option value='nom'>nom</option>
                          </select>
                        </label>
                        <button type='submit'> Trier </button>
                      </form>";

  }

  public function makePriceList($pricestorage) {
    $this->title.= "Accueil";
    $paix ="  <ul class='listePN'> <li class='l1'> Prix Nobel de la Paix <ul class='sousliste4'>";
    $physique =" </ul> </li> <li class='l2'> Prix Nobel de Physique <ul class='sousliste2'>";
    $chimie ="</ul> </li> <li class='l3'> Prix Nobel de Chimie <ul class='sousliste3'>";
    $litterature ="</ul> </li> <li class='l4'> Prix nobel de Littérature <ul class='sousliste1'>";

    foreach ($pricestorage as $key => $value) {
      $data = $value['nom']." : ".$value['date_prix'];
      $datadif="";
      if (key_exists('action',$_GET) && $value['utilisateur']===$_SESSION['utilisateur']) {
        $datadif = "<li><a href='".$this->router->getNobelPriceURLById($value['id'])."' >".$data."</a></li>";
      }
      if (empty($_GET) && key_exists('username',$_SESSION)){
        $datadif = "<li><a href='".$this->router->getNobelPriceURLById($value['id'])."' >".$data."</a></li>";
      }
      else if (empty($_GET)) {
        $datadif="<li>".$data."</li>";
      }
      switch ($value['type']) {
        case 'Littérature':
          $litterature.= $datadif;
          break;
        case 'Chimie':
          $chimie.= $datadif;
          break;
        case 'Physique':
          $physique.= $datadif;
          break;
        case 'Paix':
          $paix.= $datadif;
          break;
      }
    }

    $this->content.= $paix.$physique.$chimie.$litterature."</ul></li></ul>";
$this->content.= "<form action='".$this->router->getHomePageURL()."' method='POST'>
                        <label>Comment souhaitez vous trier les prix nobels ?
                          <select name='type_tri'>
                            <option value='date_prix'>date</option>
                            <option value='type'>type</option>
                            <option value='nom'>nom</option>
                          </select>
                        </label>
                        <button type='submit'> Trier </button>
                      </form>";
  }

  public function makeConnectionPage() {
    $this->title.= "Connexion";
    $this->content.= "<form class='formConnect' action='".$this->router->getURLCheckLogin()."' method='post'>
    <label>Username : <input type='text' name='username'></label> <br/><br/><br/>
    <label>Password : <input type='password' name='password' ></label> <br/><br/><br/>
    <div><input type='checkbox' id='remember' name='remember'>
       <label for='remember'>Se souvenir de moi</label> </div>
    <button type='submit'> Connexion </button>";
     if (!empty($_SESSION['utilisateur'])) {
      $this->content.="<a href='".$this->router->getDecoURL()."'> Déconnexion </a>";
    }
    $this->content.= "</form>
    You don't have account ? <a href='".$this->router->getRegisterURL()."'> Register here </a>";
  }

  public function makeRegisterPage(UserBuilder $userbuilder){
    $this->title.= "Création compte";
    $this->content.="<form class='formRegister' action='".$this->router->getURLRegisterSave()."' method='post'>
    <label>Username : <input type='text' name='username' ></label> <br/><br/><br/>
    <label>Password : <input type='password' name='password' ></label> <br/><br/><br/>
    <label>Confirm your Password : <input type='password' name='confirm_password' ></label> <br/><br/><br/>
    <button type='submit'> S'inscrire </button>
    </form>";
  }

  public function makeInformationPage() {
    $this->title.= "&Agrave; propos";
    $this->content.=
    "<h4>Groupe 7</h4>
    <article class='global'>
    <ul class='etudiant'>
    <li>Mangnan Hugo : 21908952</li>
    <li>Gosse Paul : 21811309</li>
    <li>Caillot Kilian : 21813965</li>
    <li>Chauveau Antoine : 21809928</li>
    </ul>

    <h4>Points réalisés</h4>
    <ul class='pointsrealiser'>
    <li>Site heberger sur le dev</li>
    <li>Dépôt du code sur la forge</li>
    <li>Architecture MVCR</li>
    <li>Création et gestion des bases de donnée</li>
    <li>Création de compte</li>
    <li>Système d'authentification</li>
    <li>Ajout d'un prix nobel</li>
    <li>Modification des prix</li>
    <li>Suppression des prix ajoutés</li>
    </ul>

    <h4>Compléments</h4>
    <ul class='complément'>
    <li>Site responsive</li>
    <li>Tri de la liste des objets</li>
    <li>Fonctionnalité rester connecté</li>
    </ul>

    <h4>Répartition des tâches</h4>
    <p class='repartitiontravail'></p>
    <ul>
    <li>Antoine :</li>
    </ul>
    <p>Site hébergé sur le dev, architecture MVCR, dépôt du code sur la forge, ajout d'un prix, création de compte, système d'authentification, mise en place des formulaires, requête SQL, W3C, factorisation du code, tri de la liste d'objets</p>
    <ul>
    <li>Hugo :</li>
    </ul>
    <p>Site hébergé sur le dev, dépôt du code sur la forge, ajout d'un prix, modifier un prix, suppression d'un prix, mise en place des formulaires, fonctionnalité rester connecté, requête SQL, déplacement appel à la vue dans le contrôleur</p>
    <ul>
    <li>Kilian :</li>
    </ul>
    <p>Site hébergé sur le dev, dépôt du code sur la forge, ajout de condition pour formulaire, responsive design, css, fonctionnalité rester connecté, requête SQL</p>
    <ul>
    <li>Paul :</li>
    </ul>
    <p>Site hébergé sur le dev, dépôt du code sur la forge, création et gestion des bases de donnée, mise en place des formulaires, ajout de prix, requête SQL, déplacement appel à la vue dans le contrôleur</p>
    <h4>Choix design, modélisation</h4>
      <p class='choixdesignmodel'>design: couleur adaptée au thème (dorée/prix nobel) <br> modélisation: choix de ne faire qu'une vue, limiter le nombre d'include pour ne pas alourdir le code</p>
    </article>";
  }


  public function makeDetailPricePage($price) {
    $this->title.= $price['date_prix']." ".$price['nom'];
    $this->content.= "Domaine : ".$price['type']."<br/>Le prix Nobel de ".$price['date_prix']." a été remporté par ".$price['nom']." ".$price['prenom']."<br/> Origine : ".$price['pays'];
    if (!empty($_SESSION) && $_SESSION['utilisateur']==$price['utilisateur']) {
     $this->content.="<form action='".$this->router->getDeletePricePage()."' method='POST'> <br/>
     <input class='hide' type='text' name='id' value='".$price['id']."'>
     <button type='submit'> Supprimer </button> </form>
     <form action='".$this->router->getURLModifPrice($price['id'])."' method='POST'> <button type='submit'> Modifier </button> </form>";
   }


  }

  public function makeNewPricePage(PriceBuilder $pb){
    $this->title.= "Ajout d'un nouveau prix";
    $this->content.="<form class='formNewPrice' action='".$this->router->getURLPriceSave()."' method='post'>
    <h3>Choix du Prix:</h3>
    <input type='radio' id='box_Paix' name='choix_prix' value='Paix'>
    <label for='box_Paix'> Paix</label><br/>
    <input type='radio' id='box_Physique' name='choix_prix' value='Physique'>
    <label for='box_Physique'> Physique</label><br/>
    <input type='radio' id='box_Chimie' name='choix_prix' value='Chimie'>
    <label for='box_Chimie'> Chimie</label><br/>
    <input type='radio' id='box_Litterature' name='choix_prix' value='Littérature'>
    <label for='box_Litterature'> Littérature</label><br/><br/><br/>
    <label>Prénom : <input type='text' name='prenom' required></label> <br/><br/><br/>
    <label>Nom : <input type='text' name='nom' required></label> <br/><br/><br/>
    <label>Année : <input type='text' name='annee' required></label> <br/><br/><br/>
    <label>Pays : <input type='text' name='pays' required></label> <br/><br/><br/>
    <button type='submit'> Soumettre </button>
    </form>";
  }

  public function displayPriceCreationSuccess($id){
    $this->router->POSTredirect($this->router->getNobelPriceURLById($id), "Le prix a bien été créé !");
  }

  public function displayAlteredPriceSuccess(){
    $this->router->POSTredirect($this->router->getAddedPricesPage(), "Le prix a bien été modifié !");
  }

  // public function displayConnection(){
  //   $this->router->POSTredirect($this->router->getSignInPageURL(), "Connexion réussie !");
  // }

  public function makeModifPricePage($pricestorage){
    $this->title.= "Modification d'un prix";
    $this->content.="<form class='formModifPrice' action='".$this->router->getURLSubModif()."' method='post'>
    <h3>Modification du Prix:</h3>";

    foreach ($pricestorage as $key => $value) {

      if (isset($_GET['id']) && $value['id']===$_GET['id']){
      if ($value['utilisateur']===$_SESSION['utilisateur']){

          if  ($value['type']==='Paix'){
            $this->content.="<input type='radio' id='box_Paix' name='choix_prix' value='Paix' checked>";
          }
          else{
            $this->content.="<input type='radio' id='box_Paix' name='choix_prix' value='Paix' >";
          }

          $this->content.="<label for='box_Paix'> Paix</label><br/>";

          if  ($value['type']==='Physique'){
            $this->content.="<input type='radio' id='box_Physique' name='choix_prix' value='Physique' checked>";
          }
          else{
            $this->content.="<input type='radio' id='box_Physique' name='choix_prix' value='Physique'>";
          }

          $this->content.="<label for='box_Physique'> Physique </label><br/>";

          if  ($value['type']==='Chimie'){
            $this->content.="<input type='radio' id='box_Chimie' name='choix_prix' value='Chimie' checked>";
          }
          else{
            $this->content.="<input type='radio' id='box_Chimie' name='choix_prix' value='Chimie' >";
          }

          $this->content.="<label for='box_Chimie'> Chimie</label><br/>";


          if  ($value['type']==='Littérature'){
            $this->content.="<input type='radio' id='box_Litterature' name='choix_prix' value='Littérature' checked>";
          }
          else{
            $this->content.="<input type='radio' id='box_Litterature' name='choix_prix' value='Littérature' >";
          }

          $this->content.="<label for='box_Litterature'> Littérature</label><br/><br/><br/>";





      $this->content.="<label>Prénom : <input type='text' name='prenom' required value=".$value['prenom']."></label> <br/><br/><br/>
        <label>Nom : <input type='text' name='nom' required value=".$value['nom']."></label> <br/><br/><br/>
        <label>Année : <input type='text' name='annee' required value=".$value['date_prix']."></label> <br/><br/><br/>
        <label>Pays : <input type='text' name='pays' required value=".$value['pays']."></label> <br/><br/><br/>
        <input class='hide' type='text' name='id' value='".$value['id']."'>
        <button type='submit'> Soumettre </button>
        </form>";
        }
      }
    }
  }





}
 ?>
