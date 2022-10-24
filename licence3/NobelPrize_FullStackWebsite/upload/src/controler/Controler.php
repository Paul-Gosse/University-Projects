<?php


  class Controler {
    private $view;
    private $priceDataBase;
    private $currentUser;
    private $currentPrice;

    public function __construct($view, $priceDataBase) {
      $this->view = $view;
      $this->priceDataBase = $priceDataBase;
      $this->currentUser= key_exists('currentUser', $_SESSION) ? $_SESSION['currentUser'] : null;
      $this->currentPrice= key_exists('currentPrice', $_SESSION) ? $_SESSION['currentPrice'] : null;
    }

    public function showinformation() {
        $this->view->makeDetailPricePage($this->priceDataBase->getDataBase()->getPriceDBById($_GET['id']));

    }

    public function newLogin(){
      $this->view->makeConnectionPage();
    }
    public function showAPropos(){
      $this->view->makeInformationPage();
    }

    public function newUser(){
      if ($this->currentUser==null){
        $this->currentUser=new UserBuilder(array('username' => '', 'password' => ''));
      }
      $this->view->makeRegisterPage($this->currentUser);
    }
    public function saveNewUser($data) {
      $u = new UserBuilder($data);
      if ($u->isValid()) {
        $u = $u->createUser();
        $this->priceDataBase->insert($u);
        $this->view->makeConnectionPage();
      }
      else {
        $this->view->setContent($u->getError());
        $this->view->makeRegisterPage($u);
      }
    }

    public function checkLogin() {
      $table = $this->priceDataBase->getLoginTable();
      $userN = $_POST['username'];
      $pw = $_POST['password'];

      foreach ($table as $key => $value) {
        if ($value['username'] === $userN && password_verify($pw,$value['password'])) {
          $_SESSION['username'] = $value['username'];
          $_SESSION['password'] = $value['password'];
          $_SESSION['utilisateur'] = $value['utilisateur'];
          $this->view->setContent("Vous êtes connecté !<br/>");

          return;
        }

        if(empty($userN) && empty($pw)){
          $this->view->setContent("Veuillez renseinger tous les champs");
          break;
        }


      }
      if(!empty($userN) && !empty($pw)){

        $this->view->setContent("Mot de passe ou nom d'utilisateur incorrect");
      }

      $this->view->makeConnectionPage();
      // $this->view->displayConnection();
    }

    public function rememberMe(){
      if (isset($_SESSION['username'])){
        $token = base64_encode($_SESSION['username']);
        setcookie("authToken", $token, time()+3600);
      }
    }

    public function disconnect(){
      session_unset();
      session_destroy();
      if (isset($_COOKIE['authToken'])) {
        unset($_COOKIE['authToken']);
        setcookie('authToken', null, -1);
      }
      $this->view->makeConnectionPage();
      // $this->view->displayConnection();

    }

    public function newPricePage(){
      if ($this->currentPrice==null){
        $this->currentPrice=new PriceBuilder(array('choix_prix'=>'','prenom'=>'','nom'=>'','annee'=>'','pays'=>''/*, 'commentaire'=>''*/));
      }
      $this->view->makeNewPricePage($this->currentPrice);
    }
    public function saveNewPrice($data) {
      $prix=new PriceBuilder($data);
      if ($prix->isValid()){
        $prix=$prix->createPrice();
        $this->priceDataBase->insertPrix($prix);
        $this->view->displayPriceCreationSuccess($this->priceDataBase->getPrixId());
      }
      else{
        $this->view->setContent($prix->getError());
        $this->view->makeNewPricePage($prix);
      }
      }

      public function modifPrice($id) {
        $this->priceDataBase->updatePrice($id);
        $this->view->displayAlteredPriceSuccess();
        }

      public function delPrice($id){
        $this->priceDataBase->delPrice($id);
        $this->view->displayAlteredPriceSuccess();
      }

      public function showAddedPrices(){
        $this->view->makePriceList($this->priceDataBase->getPriceData());
      }

      public function showModifPrice(){
        $this->view->makeModifPricePage($this->priceDataBase->getPriceData());
      }


  }
 ?>
