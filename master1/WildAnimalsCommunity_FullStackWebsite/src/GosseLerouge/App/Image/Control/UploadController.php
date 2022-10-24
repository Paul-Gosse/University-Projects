<?php

namespace GosseLerouge\App\Image\Control;
use GosseLerouge\Framework\Control\AbstractController;

class UploadController extends AbstractController
{
    public function show()
    {
        if ($this->authenticationManager->isUserConnected()) {
            return $this->twig->render('upload.html.twig', [
                'title' => "Ajout de fichiers",
                'connectionInfo' => $this->getConnectionInfo(),
                'message' => $this->interpretMessage(),
            ]);
        } else {
            $this->request->setSession("message", "connection-required");
            header("Location: index.php");
        }
        
    }

    public function receiveImage()
    {
        var_dump($_POST);
    }


    public function showPost()
    {
        var_dump($_POST);
        var_dump($_POST["scrappingImage"]);
    }

	public function insertAnimal()
    {
		$name = $_POST['name'];
        $file_name = "";
        $file_data = "";
        $link = $_POST['link'];
        $longitude = $_POST['longitude'];
        $latitude = $_POST['latitude'];
        $categorie = $_POST['categorie'];
        $description = "";
        $wikilink = '';
        $scrapped = 0;

        //file_put_contents("test.jpg", base64_encode($file));
        var_dump($_POST);
        
        if(!empty($_POST['scrappingImage'])){
            $wikilink = $_POST['scrappingImage'];
        } else {
            $file_data = file_get_contents($_FILES['file']['tmp_name'][0]);
        }

        if(!empty($_POST['scrappingDescription'])){
            $description = $_POST['scrappingDescription'];
            $scrapped = 1;
        } else {
            $description = $_POST['description'];
            $scrapped = 0;
        }

        if(!empty($_POST['file_name'])){
            $file_name = $_POST['file_name'];
        } else {
            $file_name = $_FILES['file']['name'][0];
        }
        
        $sql = "INSERT INTO `Animaux`(`nom`, `file_name`, `file_data`, `link`, `longitude`, `latitude`, `categorie`, `description`, `wikilink`, `scrapped`) 
                VALUES (:nom, :file_name, :file_data, :link, :longitude, :latitude, :categorie, :description, :wikilink, :scrapped)";
        $res = $this->dbh->prepare($sql);
        $value = $res->execute(array(":nom"=>$name,":file_name"=>$file_name,":file_data"=>$file_data,":link"=>$link,":longitude"=>$longitude,":latitude"=>$latitude,
                                     ":categorie"=>$categorie,":description"=>$description,":wikilink"=>$wikilink, ":scrapped"=>$scrapped));
        
        
                                     if($value){
            $this->request->setSession("message", "uploaded-animal-success");
        } else {
            $this->request->setSession("message", "uploaded-animal-failed");
        }
	}
}
