<?php

  namespace App\Models;

  class App
  {
      public static function header($type, $value)
      {
        header($type . ": " . $value);
      }

      public static function redirect($to)
      {
        App::header("location", $to);
      }
  }
