<?php

namespace GosseLerouge\App\Image\Control;
use GosseLerouge\Framework\Control\AbstractController;
use \PDO;

class EditController extends AbstractController
{

    public function show()
    {
        if ($this->authenticationManager->isUserConnected()) {
            $data = $this->getData($_GET['id']);
            return $this->twig->render('edit.html.twig', [
                'title' => "Modification des animaux",
                'connectionInfo' => $this->getConnectionInfo(),
                'message' => $this->interpretMessage(),
                'data_db' => $data,
                'filedata' => base64_encode($data[0]->file_data),
            ]);
        } else {
            $this->request->setSession("message", "connection-required");
            header("Location: index.php");
        }
    }

    public function getData($id)
	{
		$result = $this->dbh->prepare("SELECT * FROM Animaux WHERE id=$id");
		$result->execute();
        $array = $result->fetchAll(PDO::FETCH_OBJ);
		return $array;
    }

    public function receiveImage()
    {
        var_dump($_POST);
    }

    
    public function updateAnimal()
    {
        $id = $_POST['id'];
        $init = $this->getData($id);
        $name = $_POST['name'];
        $file_name = "";
        $file_data = "";
        $description = "";
        $link = "";
        $longitude = $_POST['longitude'];
        $latitude = $_POST['latitude'];
        $categorie = $_POST['categorie'];
        $wikilink = '';
        $scrapped = $init[0]->scrapped;

        var_dump($_POST);

        if(!empty($_POST['link'])){
            $link = $_POST['link'];
        }

        if(!empty($_POST['description'])){
            $description = $_POST['description'];
            $scrapped = 0;
        }

        if(!empty($_POST['scrappingImage'])){
            $wikilink = $_POST['scrappingImage'];
            var_dump($wikilink);
        } else {
            $file_data = file_get_contents($_FILES['file']['tmp_name'][0]);
        }

        if(!empty($_POST['scrappingDescription'])){
            $description = $_POST['scrappingDescription'];
            $scrapped = 1;
        }

        if(!empty($_POST['file_name'])){
            $file_name = $_POST['file_name'];
        } else {
            $file_name = $_FILES['file']['name'][0];
        }

        $array = array(
            "id" => $id,
            "nom" => $name,
            "file_name" => $file_name,
            "file_data" => $file_data,
            "link" => $link,
            "longitude" => $longitude,
            "latitude" => $latitude,
            "categorie" => $categorie,
            "description" => $description,
            "wikilink" => $wikilink,
            "scrapped" => $scrapped
        );

        $arrayModif = array();

        foreach($init[0] as $key => $value){
            if($value !== $array[$key] && $array[$key] !== ''){
                $arrayModif[$key] = $array[$key];
                var_dump($value);
            } else {
                $arrayModif[$key] = $value;
            }
        }

        if(($arrayModif["wikilink"] === $_POST['scrappingImage']) && (!empty($arrayModif["wikilink"]))){
            $arrayModif["file_data"] = "";
        } else {
            $arrayModif['wikilink'] = "";
        }
                
        $sql = "UPDATE `Animaux` SET `nom`=:nom, `file_name`=:file_name, `file_data`=:file_data, `link`=:link, `longitude`=:longitude, `latitude`=:latitude, `categorie`=:categorie, 
        `description`=:description, `wikilink`=:wikilink, `scrapped`=:scrapped WHERE id = :id";
        $res = $this->dbh->prepare($sql);
        $value = $res->execute($arrayModif);
        if($value){
            $this->request->setSession("message", "update-animal-success");
        } else {
            $this->request->setSession("message", "update-animal-failed");
        }
    }
}