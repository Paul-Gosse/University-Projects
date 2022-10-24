<?php
require_once "view/View.php";

require_once "lib/DataBaseStore.php";

require_once "controler/Controler.php";

require_once "model/Price.php";
require_once "model/User.php";
require_once "model/PriceStorage.php";
require_once "model/UserBuilder.php";
require_once "model/PriceBuilder.php";

class Router {

  function main() {


    $feedback = key_exists('feedback', $_SESSION) ? $_SESSION['feedback'] : '';
    $_SESSION['feedback'] = '';
    $view = new View($this,$feedback);

    $bdd = new dataBaseStore();
    $pricestorage = new PriceStorage($bdd);

    $controler = new Controler($view,$pricestorage);
    //echo ($pricestorage->getDataBase()->getPriceId());
    //var_dump($pricestorage->getDataBase()->getPriceDB());
    //var_dump($_SESSION);
    //var_dump($pricestorage->getLoginTable());
    if (isset($_COOKIE['authToken'])){
      $_SESSION['username']=base64_encode($_COOKIE['authToken']);
    }
    if (empty($_GET)){
	if (empty($_POST)) {
        	$view->makePriceList($pricestorage->getPriceData());
      	} else {
        	$view->makePriceListSorted($pricestorage->getPriceDBSorted($_POST['type_tri']));
      }
    }
    else{
      switch ($_GET['action']) {

        case 'checklogin':
        if (!empty($_POST['username'] && !empty($_POST['password']) && !empty($_POST['remember']))){
          $controler->checkLogin();
          $controler->rememberMe();
        }
        else if (!empty($_POST['username'] && !empty($_POST['password']))){
          $controler->checkLogin();
        }
        case 'login':
          $controler->newLogin();

          break;

        case 'register':
          $controler->newUser();
          break;

        case 'information':
          $controler->showAPropos();
          break;

        case 'nouveauprix':
          $controler->newPricePage();
          break;

        case 'sauverprix':
          $controler->saveNewPrice($_POST);

          break;

        case 'listeprixajoutes':
          $controler->showAddedPrices();

        case 'supprimerprix':
          if (!empty($_POST)) {
            $controler->delPrice($_POST['id']);
          }
          break;

        case 'modifierprix':
          $controler->showModifPrice();
          break;

        case 'savemodifierprix':
          $controler->modifPrice($_POST['id']);
          break;

        case 'prix':
          $controler->showinformation();
          break;

        case 'registersave':
          $controler->saveNewUser($_POST);

          break;


        case 'disconnection':
          $controler->disconnect();
          break;



        break;
        }

    }
    $view->makeNav();
    $view->render();
  }

  public function POSTredirect($url,$feedback){
    $_SESSION['feedback'] = $feedback;
		header("Location: ".htmlspecialchars_decode($url), true, 303);
		die;
    }

  public function getURLHomePageConnected() {
    return 'Prixnobel.php?action=login&log=oui';
  }

  public function getRegisterURL() {
    return 'Prixnobel.php?action=register';
  }

  public function getDecoURL() {
    return 'Prixnobel.php?action=disconnection';
  }

  public function getURLRegisterSave() {
    return 'Prixnobel.php?action=registersave';
  }

  public function getHomePageURL() {
    return 'Prixnobel.php';
  }

  public function getInformationPageURL() {
    return 'Prixnobel.php?action=information';
  }

  public function getSignInPageURL() {
    return 'Prixnobel.php?action=login';
  }

  public function getURLCheckLogin(){
    return 'Prixnobel.php?action=checklogin';

  }
  public function getNobelPriceURLById($id) {
    return 'Prixnobel.php?action=prix&id='.$id;
  }

  public function getNewPricePage(){
    return 'Prixnobel.php?action=nouveauprix';
  }

  public function getDeletePricePage(){
    return 'Prixnobel.php?action=supprimerprix';
  }

  public function getURLPriceSave() {
    return 'Prixnobel.php?action=sauverprix';
  }

  public function getURLModifPrice($id) {
    return 'Prixnobel.php?action=modifierprix&id='.$id;
  }

  public function getURLSubModif(){
    return 'Prixnobel.php?action=savemodifierprix&sub=oui';
  }

  public function getAddedPricesPage(){
    return 'Prixnobel.php?action=listeprixajoutes';
  }

}
 ?>
