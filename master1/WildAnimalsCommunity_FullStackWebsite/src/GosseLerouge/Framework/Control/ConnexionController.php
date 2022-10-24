<?php

namespace GosseLerouge\Framework\Control;

use \PDO;
use GosseLerouge\Framework\Control\AbstractController;
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;



class ConnexionController extends AbstractController
{

    public function show()
    {
        if(isset($_POST["Creation"])){
            return $this->twig->render('create.html.twig', [
                'title' => "Créer un compte",
                'connectionInfo' => $this->getConnectionInfo(),
                'message' => $this->interpretMessage(),
            ]);
        } else if(isset($_POST['Connexion'])){
            return $this->twig->render('connect.html.twig', [
                'title' => "Connexion",
                'connectionInfo' => $this->getConnectionInfo(),
                'message' => $this->interpretMessage(),
            ]);
        } else {
            return $this->twig->render('connect.html.twig', [
                'title' => "Connexion",
                'connectionInfo' => $this->getConnectionInfo(),
                'message' => $this->interpretMessage(),
            ]);
        }
    }

    public function create()
    {
        if (isset($_POST['input-name']) && isset($_POST['input-password'])) {
            $username = $_POST['input-name'];
            $pwd = $_POST['input-password'];
            $email = $_POST['input-email'];
            $hashedpwd = password_hash($pwd, PASSWORD_DEFAULT);
            $URL = explode("/",$_SESSION["previousURI"]);
            $redirectURL = ($URL[sizeof($URL)-1] !== "") ? $URL[sizeof($URL)-1] : "index.php";

            $sqlSelect = "SELECT * FROM `Users` WHERE `username` = :username";
            $resSelect = $this->dbh->prepare($sqlSelect);
            $resSelect->execute(array(":username" => $username));
            $arraySelect = $resSelect->fetchAll(PDO::FETCH_OBJ);

            if(sizeof($arraySelect) == 0){
                $sql = "INSERT INTO `Users` (`username`, `pswd`, `email`, `rolestatus`) VALUES (:username, :pswd, :email, :rolestatus)";
                $res = $this->dbh->prepare($sql);
                $res->execute(array(":username" => $username, ":pswd" => $hashedpwd, ":email" => $email, ":rolestatus" => "admin"));
                $array = $res->fetchAll(PDO::FETCH_OBJ);

                $this->request->setSession("username", $username);
                $this->request->setSession("email", $email);
                $this->request->setSession("status", "admin");
                $this->request->setSession("message", "authentication-succeed");
                $this->sendMailCreatedAccount($email, $username);
                header('Location:' . $redirectURL);
            } else {
                $this->request->setSession("message", "creation-failed");
                header('Location:' . $redirectURL);
            }

            
        }
    }

    public function changePassword()
    {
        if(isset($_POST['input-old-password']) && isset($_POST['input-new-password'])){
            $username = $this->request->getSession("username");
            $email = $this->request->getSession("email");
            $oldpwd = $_POST['input-old-password'];
            $newpwd = $_POST['input-new-password'];

            $hashednewpwd = password_hash($newpwd, PASSWORD_DEFAULT);

            $URL = explode("/",$_SESSION["previousURI"]);
            $redirectURL = ($URL[sizeof($URL)-1] !== "") ? $URL[sizeof($URL)-1] : "index.php";

            $sqlSelect = "SELECT * FROM `Users` WHERE `username` = :username";
            $resSelect = $this->dbh->prepare($sqlSelect);
            $resSelect->execute(array(":username" => $username));
            $arraySelect = $resSelect->fetchAll(PDO::FETCH_OBJ);

            foreach ($arraySelect as $key => $value) {
                if($username === $value->username and password_verify($oldpwd, $value->pswd)) {
                    $sqlUpdate = "UPDATE `Users` SET `pswd` = :pwd WHERE `Users`.`username` = :username";
                    $resUpdate = $this->dbh->prepare($sqlUpdate);
                    $resUpdate->execute(array(":pwd" => $hashednewpwd, ":username" => $username));
                    $this->request->setSession("message", "modification-pswd-succeed");
                    $this->sendMailPasswordChanged($email, $username);
                    header('Location:' . $redirectURL);
                    return true;
                }
            }
            $this->request->setSession("message", "modification-failed");
            header("Location:index.php"); 
        }
    }

    public function sendMailCreatedAccount($email, $username)
    {
        //Create an instance; passing `true` enables exceptions
        $mail = new PHPMailer(true);

        //Recipients
        $mail->setFrom('animauxsauvages@example.org', 'Animaux Sauvages');
        $mail->addAddress($email);     //Add a recipient
        $mail->addReplyTo('animauxsauvages@example.org', 'Animaux Sauvages');

        //Content
        $mail->isHTML(true);                                  //Set email format to HTML
        $mail->Subject = 'Bienvenue !';
        $mail->Body    = 'Bienvenue <strong>'. $username .'</strong> <br />Nous esperons que vous prendrez du plaisir a utiliser notre site';
        $mail->AltBody = 'Nous esperons que vous prendrez du plaisir a utiliser notre site';

        $mail->send();
    }

    public function sendMailPasswordChanged($email, $username)
    {
        //Create an instance; passing `true` enables exceptions
        $mail = new PHPMailer(true);

        //Recipients
        $mail->setFrom('animauxsauvages@example.org', 'Animaux Sauvages');
        $mail->addAddress($email);     //Add a recipient
        $mail->addReplyTo('animauxsauvages@example.org', 'Animaux Sauvages');

        //Content
        $mail->isHTML(true);                                  //Set email format to HTML
        $mail->Subject = 'Changement de mot de passe';
        $mail->Body    = 'Bonjour <strong>'.$username.'</strong>. <br /> Nous vous informons que votre mot de passe a bien ete modifie.';
        $mail->AltBody = 'Nous vous informons que votre mot de passe à bien ete modifie.';

        $mail->send();
    }

    public function verify()
    {
        if (isset($_POST['input-name']) and isset($_POST['input-password'])) {
            $username = $_POST['input-name'];
            $pwd = $_POST['input-password'];
            $hashedpwd = password_hash($pwd, PASSWORD_DEFAULT);
            $URL = explode("/",$_SESSION["previousURI"]);
            $redirectURL = ($URL[sizeof($URL)-1] !== "") ? $URL[sizeof($URL)-1] : "index.php";

            $sql = "SELECT * FROM `Users` WHERE `username` = :username";
            $res = $this->dbh->prepare($sql);
            $res->execute(array(":username" => $username));
            $array = $res->fetchAll(PDO::FETCH_OBJ);

            if(empty($array))
            {
                $this->request->setSession("message", "authentication-failed");
                header('Location:index.php');
            }

            foreach ($array as $key => $value) {
                if($username === $value->username and password_verify($pwd, $value->pswd)) {
                    $this->request->setSession("username", $username);
                    $this->request->setSession("email", $value->email);
                    $this->request->setSession("status", $value->rolestatus);
                    $this->request->setSession("message", "authentication-succeed");
                    header('Location:' . $redirectURL);
                    return true;
                } 
            }
            $this->request->setSession("message", "authentication-failed");
            header('Location:index.php');
        }
        
    }

    public function disconnect() : void
    {
        $this->request->destroySession();
        $this->request->setSession("message", "disconnected");
        $redirectURL = "index.php";
        header("Location: " . $redirectURL);
    }

    public function getConnectionInfo(): array
    {
        $connectionInfo = [];
        if (isset($_SESSION['username'])) {
            $connectionInfo["connectionState"] = true;
            $connectionInfo["username"] = $_SESSION['username'];
            $connectionInfo["status"] = $_SESSION['status'];
        } else {
            $connectionInfo["connection-state"] = false;
        }
        return $connectionInfo;
    }

}