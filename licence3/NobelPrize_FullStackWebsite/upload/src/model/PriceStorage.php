<?php
  class PriceStorage {
    private $priceDataBase;

    public function __construct($priceDataBase) {
      $this->priceDataBase = $priceDataBase;
    }

    public function getDataBase() {
      return $this->priceDataBase;
    }

    public function getPricedata() {
      return $this->priceDataBase->getPriceDB();
    }

    public function getLoginTable() {
      return $this->priceDataBase->getUserdb();
    }
    public function insert($u) {
      $this->priceDataBase->insertUser($u);
    }

    public function insertPrix($p){
      $this->priceDataBase->insertPrice($p);
    }
    public function delPrice($id) {
      $this->priceDataBase->delPrice($id);
    }

    public function updatePrice($id) {
      $this->priceDataBase->updatePrice($id);
    }

    public function getPrixId(){
      return $this->priceDataBase->getPriceId();
    }

    public function getPriceDBSorted($sorttype)
    {
      return $this->priceDataBase->getSortedPriceDB($sorttype);
    }

  }
 ?>
