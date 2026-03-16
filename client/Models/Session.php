<?php

  namespace App\Models;

  class Session
  {
    public static function exists($name = "Auth")
    {
      return isset($_SESSION[$name]);
    }

    public static function getId($name = "Auth")
    {
      return $_SESSION[$name]["id"];
    }

    public static function getUserName($name = "Auth")
    {
      return $_SESSION[$name]["username"];
    }

    public static function getAvatar($name = "Auth")
    {
      return $_SESSION[$name]["avatar"]
    }
  }
