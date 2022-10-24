<?php

namespace GosseLerouge\App\Image\Control;
use GosseLerouge\Framework\Control\AbstractController;
use \PDO;

class DetailsController extends AbstractController
{
    public function show()
    {
        if ($this->authenticationManager->isUserConnected()) {
            if(!empty($_POST['remove'])){
                $this->delete($_POST['remove']);
            }
            $data = $this->getData($_GET['id']);
            $data2 = $this->getCommentsFromPage($_GET['id']);
            $data3 = $this->getNumberOfComments($_GET['id']);
            if(empty($data)){
                header("location:index.php?o=listEdit&a=show");
            } else {
                return $this->twig->render('details.html.twig', [
                    'title' => "Modification des animaux",
                    'connectionInfo' => $this->getConnectionInfo(),
                    'message' => $this->interpretMessage(),
                    'data_db' => $data,
                    'filedata' => base64_encode($data[0]->file_data),
                    'comments' => $data2,
                    'numberComments' => $data3[0]->total_comments,
                    'currentUser' => $this->request->getSession("username")
                ]);
            }
        } else {
            $this->request->setSession("message", "connection-required");
            header("Location: index.php");
        }
    }

    public function getCommentsFromPage($id)
    {
        $result = $this->dbh->prepare("SELECT * from comments WHERE animal_id=$id ORDER BY submit_date DESC");
        $result->execute();
        $array = $result->fetchAll(PDO::FETCH_OBJ);
        return $array;
    }

    public function getNumberOfComments($id)
    {
        $result = $this->dbh->prepare("SELECT COUNT(*) AS total_comments FROM comments WHERE animal_id = $id");
        $result->execute();
        $array = $result->fetchAll(PDO::FETCH_OBJ);
        return $array;
    }

    public function writeCommentary()
    {
        date_default_timezone_set('GMT');
        $name = $this->request->getSession("username");
        $content = $_POST['content'];
        $id = $_POST['id'];
        $date = date('Y-m-d');


        $sql = "INSERT INTO `comments` (`animal_id`, `name`, `content`, `submit_date`) VALUES (:animal_id, :name, :content, :submit_date)";
        $result = $this->dbh->prepare($sql);
        $result->execute(array(":animal_id"=>$id,":name"=>$name,":content"=>$content,":submit_date"=>$date));
        if($result){
            $this->request->setSession("message", "post-commentary-success");
        } else {
            $this->request->setSession("message", "post-commentary-failed");
        }
        
        header("location:index.php?o=details&a=show&id=".$id);
    }

    public function suppressCommentary($id)
    {
        $sql = "DELETE FROM `comments` WHERE id = $id";
        $result = $this->dbh->prepare($sql);
        $result->execute();
    }

    public function getData($id)
    {
        $result = $this->dbh->prepare("SELECT * FROM Animaux WHERE id=$id");
        $result->execute();
        $array = $result->fetchAll(PDO::FETCH_OBJ);
        return $array;
    }

    public function delete($id)
    {
        $result = $this->dbh->prepare("DELETE FROM comments WHERE id=$id");
        $result->execute();
    }
}