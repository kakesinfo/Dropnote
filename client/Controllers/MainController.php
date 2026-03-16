<?php

  namespace App\Controllers;

  class MainController extends Controller
  {
      public function __construct()
      {
        $title = "Hello world !";

        $this->render("default.main", compact("title"), 0);
      }
  }
