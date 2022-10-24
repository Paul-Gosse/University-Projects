<?php

namespace GosseLerouge\Framework;

use GosseLerouge\Framework\Request;

class AuthenticationManager implements AuthenticationManagerInterface
{

    protected Request $request;

    /**
     * AuthenticationManager constructor.
     */
    public function __construct(Request $request)
    {
        $this->request = $request;
    }

    public function execute($action)
    {
        return $this->$action();
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

    public function isUserConnected() : bool
    {
        return isset($_SESSION['username']);
    }
}