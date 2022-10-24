<?php
  require_once "View.php";
 ?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="skin/Style.css" />
    <title><?php echo $this->title ?></title>
  </head>
  <body>

    <?php echo $this->content ?>
    <?php if ($this->feedback !== '') { ?>
       <div class="feedback"><?php echo $this->feedback; ?></div>
    <?php } ?>
  </body>

</html>
