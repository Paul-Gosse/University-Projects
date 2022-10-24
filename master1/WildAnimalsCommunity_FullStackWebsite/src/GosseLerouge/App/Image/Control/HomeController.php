<?php

namespace GosseLerouge\App\Image\Control;
use GosseLerouge\Framework\Control\AbstractController;

class HomeController extends AbstractController
{
    public function show() 
    {
        $this->response->addHeader('X-Debugging: show me home page');
        $this->makeHomePage();
    }
}