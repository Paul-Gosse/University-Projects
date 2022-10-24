<?php

namespace GosseLerouge\App\Image\Control;
use GosseLerouge\Framework\Control\AbstractController;
use \PDO;

class ListEditController extends AbstractController
{
    public function show()
    {
        if ($this->authenticationManager->isUserConnected()) {
            if(!empty($_POST['remove'])){
                $this->delete($_POST['remove']);
            }

            if(!empty($_POST['sort'])){
                $post = $_POST['sort'];
            } else {
                $post = null;
            }

            return $this->twig->render('listEdit.html.twig', [
                'title' => "Modification des la liste d'animaux",
                'connectionInfo' => $this->getConnectionInfo(),
                'message' => $this->interpretMessage(),
                'image_db' => $this->getImage(),
                'image_db_order' => $this->getImageOrder($post),
                'post' => $_POST,
            ]);
        } else {
            $this->request->setSession("message", "connection-required");
            header("Location: index.php");
        }
    }

    public function getImage()
    {
		$result = $this->dbh->prepare("SELECT id, nom, file_data, wikilink FROM Animaux");
		$result->execute();
        $array = $result->fetchAll(PDO::FETCH_ASSOC);
        for($i = 0; $i < sizeof($array); $i++){
            if($array[$i]["file_data"] !== ""){
                $array[$i]["file_data"] = base64_encode($array[$i]["file_data"]);
            }
        }
		return $array;
    }

    public function getImageOrder($cat)
    {
        $result = $this->dbh->prepare("SELECT id, nom, file_data, wikilink FROM Animaux WHERE categorie='$cat'");
		$result->execute();
        $array = $result->fetchAll(PDO::FETCH_ASSOC);
        for($i = 0; $i < sizeof($array); $i++){
            if($array[$i]["file_data"] !== ""){
                $array[$i]["file_data"] = base64_encode($array[$i]["file_data"]);
            }
        }
		return $array;
    }

    

    public function delete($id)
    {
        $result = $this->dbh->prepare("DELETE FROM Animaux WHERE id=$id");
        $result->execute();
    }
}
