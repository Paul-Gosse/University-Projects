<?php

namespace GosseLerouge\App\Animaux\Control;

use \PDO;
use GosseLerouge\Framework\Control\AbstractController;
use GosseLerouge\App\Animaux\Control\MySQLInterface;


class MySQLOperations extends AbstractController implements MySQLInterface
{

	public function read($nameOfDB,$id)
	{
		$result = $this->dbh->prepare("SELECT * FROM ".$nameOfDB." WHERE id=".$id);
		$result->execute();
		$array = $result->fetchAll(PDO::FETCH_OBJ);
		return $array;
	}

	public function readAll($nameOfDB)
	{
		$result = $this->dbh->prepare("SELECT * FROM ".$nameOfDB);
		$result->execute();
		$array = $result->fetchAll(PDO::FETCH_OBJ);
		return $array;
	}

	public function encodeDataInJSON($result, $nameOfFile){
		$jsonPath = './images/data/'.$nameOfFile.'.json';
		$newJSON = json_encode($result, JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES);
		file_put_contents($jsonPath, $newJSON);
	}

	public function encodeDataInGeoJSONFormat($result, $nameOfFile)
	{
		$jsonPath = './images/data/'.$nameOfFile.'.json';
		$geojson = array('type' => 'FeatureCollection', 'features' => array());
		for ($i=0; $i < sizeof($result); $i++) {
			$features = array(
			'type' => 'Feature',
                'geometry' => array(
                    'type' => 'Point',
                    'coordinates' => array(
                    	$result[$i]->longitude, $result[$i]->latitude
                    )
                ),
                'properties' => array(
                    "title" => $result[$i]->nom,
                    "description" => $result[$i]->description,
                    "link" => $result[$i]->link,
                    "file_data" => base64_encode($result[$i]->file_data),
					"wikisource" => $result[$i]->wikilink,
					"categorie" => $result[$i]->categorie
                )
			);
			array_push($geojson["features"], $features);
		}
		$newJSON = json_encode($geojson, JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES);
		file_put_contents($jsonPath, $newJSON);

	}

	public function showDB()
	{
		$result = $this->readAll('Animaux');
		$this->encodeDataInGeoJSONFormat($result,'Animaux');
		return $this->makeHomePage();
	}

}
