<?php

namespace src\PaulGosse\Factoring\Control;

use src\PaulGosse\Factoring\Control\Request;

class AuthenticationManager
{
    protected $request;
    protected $utilisateur;

    function __construct(Request $request)
    {
        $this->request = $request;
        $this->utilisateur = array(
            'name1' => array(
                'nom' => 'lastname',
                'prenom' => 'name',
                'mdp' => 'password',
                'statut' => 'admin'
            ),
            'name2' => array(
                'nom' => 'lastname',
                'prenom' => 'name',
                'mdp' => 'password',
                'statut' => 'admin'
            )
        );
    }

    public function connecterUtilisateur()
    {
        $cle = 'utilisateur';
        $session = $this->request->getSession($cle);
        if (isset($_POST['utilisateur']) && !isset($session['utilisateur'])) {
            foreach ($this->utilisateur as $cle => $valeur) {
                if ($cle == $_POST['utilisateur']) {
                    if ($this->utilisateur[$cle]['mdp'] == $_POST['mdp']) {
                        $this->request->setSession('utilisateur', $valeur);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public function estConnecte()
    {
        $utilisateur = $this->request->getSession('utilisateur');
        if ($utilisateur != null) {
            return true;
        } else {
            return false;
        }
    }

    public function deconnecterUtilisateur()
    {
        unset($_SESSION['utilisateur']);
        session_destroy();
        header("Refresh:0");
    }
}
