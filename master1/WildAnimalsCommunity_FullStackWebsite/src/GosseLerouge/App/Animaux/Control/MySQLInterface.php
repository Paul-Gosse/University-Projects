<?php 

namespace GosseLerouge\App\Animaux\Control;

interface MySQLInterface
{
	public function read($nameOfDB,$id);
	public function readAll($nameOfDB);
}