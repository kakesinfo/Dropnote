<?php

  namespace App;

  require "autoloader.php";
  Autoloader::register();

  $router = new Router\Router($_GET["url"]);

  $router->all("", function() {
    new Controllers\MainController();
  });

  $router->run();
