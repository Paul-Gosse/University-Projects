<?php

namespace src\PaulGosse\Factoring\Control;

use src\PaulGosse\Factoring\Model\GalerieStorageStub;
use src\PaulGosse\Factoring\Control\Request;
use src\PaulGosse\Factoring\Control\Response;
use src\PaulGosse\Factoring\View\View;
use src\PaulGosse\Factoring\Control\AuthenticationManager;


class DetailController
{

    protected $view;
    protected $request;
    protected $response;

    function __construct(View $view, Request $request, Response $response, AuthenticationManager $authentication)
    {
        $this->view = $view;
        $this->request = $request;
        $this->response = $response;
        $this->authentication = $authentication;

        $menu = array(
            "Accueil" => '?o=galerie&a=show',
            "Upload" => '?o=upload&a=show',
            "Liste" => '?o=liste&a=show',
            "Présentation" => '?o=presentation&a=show',
            "Connexion" => '?o=connexion&a=show',
        );
        $this->view->setPart('menu', $menu);
    }

    public function execute($action)
    {
        $this->$action();
    }

    public function defaultAction()
    {
        return $this->show();
    }

    public function show()
    {
        $this->response->addHeader('X-Debugging: show me a image');
        $id = $this->request->getGet('id');
        $galerieStorage = new GalerieStorageStub();
        $galerie = $galerieStorage->read($id);
        if ($galerie !== null) {

            //extraction de métadonnées pour la page détail
            $image = $galerie->getImage();
            $extract_title = exec("exiftool -IPTC:ObjectName " . $image);
            $title = substr($extract_title, strpos($extract_title, ":") + 1);
            $content = "<figure>\n<img id='img_detail' src=\"$image\" alt=\"\" />\n";
            exec("exiftool -Exiftool:all " . $image,  $extract_exiftool);
            exec("exiftool -File:all " . $image, $extract_file);
            exec("exiftool -JFIF:all " . $image, $extract_jfif);
            exec("exiftool -IPTC:all " . $image, $extract_iptc);
            exec("exiftool -Photoshop:all " . $image, $extract_photoshop);
            exec("exiftool -ICC_Profile:all " . $image, $extract_icc_pro);
            exec("exiftool -ICC-view:all " . $image, $extract_icc_view);
            exec("exiftool -ICC-meas:all " . $image, $extract_icc_meas);
            exec("exiftool -Ifd0:all " . $image, $extract_ifd);
            exec("exiftool -ExifIFD:all " . $image, $extract_exififd);
            exec("exiftool -GPS:all " . $image, $extract_gps);
            exec("exiftool -XMP-x:all " . $image, $extract_xmp_x);
            exec("exiftool -XMP-xmp:all " . $image, $extract_xmp_xmp);
            exec("exiftool -XMP-photoshop:all " . $image, $extract_xmp_photo);
            exec("exiftool -XMP-xmpRights:all " . $image, $extract_xmp_right);
            exec("exiftool -Composite:all " . $image, $extract_compo);

            //extraction de métadonnées pour les balises meta
            $extract_source = exec("exiftool -XMP-photoshop:Source " . $image);
            $source = substr($extract_source, strpos($extract_source, ":") + 1);
            $extract_webname = exec("exiftool -XMP-photoshop:Credit " . $image);
            $name = substr($extract_webname, strpos($extract_webname, ":") + 1);
            $extract_type = exec("exiftool -File:FileType " . $image);
            $type = substr($extract_type, strpos($extract_type, ":") + 1);
            $extract_description = exec("exiftool -IFD0:ImageDescription " . $image);
            $desc = substr($extract_description, strpos($extract_description, ":") + 1);
            $extract_artist = exec("exiftool -IFD0:Artist " . $image);
            $artist = substr($extract_artist, strpos($extract_artist, ":") + 1);
            $extract_latitude = exec("exiftool -n -Composite:GPSLatitude " . $image);
            $latitude = substr($extract_latitude, strpos($extract_latitude, ":") + 1);
            $extract_longitude = exec("exiftool -n -Composite:GPSLongitude " . $image);
            $longitude = substr($extract_longitude, strpos($extract_longitude, ":") + 1);


            //recupération pour envoie vers la vue
            $this->view->setPart('title', $title);
            $this->view->setPart('content', $content);
            $this->view->setPart('exiftool', $extract_exiftool);
            $this->view->setPart('file', $extract_file);
            $this->view->setPart('jfif', $extract_jfif);
            $this->view->setPart('iptc', $extract_iptc);
            $this->view->setPart('photoshop', $extract_photoshop);
            $this->view->setPart('icc_pro', $extract_icc_pro);
            $this->view->setPart('icc_view', $extract_icc_view);
            $this->view->setPart('icc_meas', $extract_icc_meas);
            $this->view->setPart('ifd', $extract_ifd);
            $this->view->setPart('exififd', $extract_exififd);
            $this->view->setPart('gps', $extract_gps);
            $this->view->setPart('xmp-x', $extract_xmp_x);
            $this->view->setPart('xmp-xmp', $extract_xmp_xmp);
            $this->view->setPart('xmp-photo', $extract_xmp_photo);
            $this->view->setPart('xmp-right', $extract_xmp_right);
            $this->view->setPart('compo', $extract_compo);

            ////recupération pour envoie vers la vue pour les balises meta
            $this->view->setPart('source', $source);
            $this->view->setPart('type', $type);
            $this->view->setPart('name', $name);
            $this->view->setPart('desc', $desc);
            $this->view->setPart('artist', $artist);
            $this->view->setPart('latitude', $latitude);
            $this->view->setPart('longitude', $longitude);
        } else {
            $this->unknownGalerie();
        }
    }

    public function unknowngalerie()
    {
        $title = "Image inconnue";
        $content = "Choisir une image parmi la liste";
        $this->view->setPart('title', $title);
        $this->view->setPart('content', $content);
    }
}
