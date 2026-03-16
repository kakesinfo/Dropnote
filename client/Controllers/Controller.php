<?php

  namespace App\Controllers;

  use \App\Models\Cache;

  class Controller
  {
    protected $viewPath = "Views/";
    protected $templatePath = "Templates/";

    protected $cache = null;

    public function render($view, $variables = [], $delay, $view_prefix = "")
    {
      $template = explode('.', $view)[0];

      if($view_prefix != null)
      {
        $view_prefix .= ".";
      }

      if($this->cache == null)
      {
        $this->cache = new Cache($view_prefix . $view, $delay);
      }

      if(!$this->cache->start())
      {
        extract($variables);

        require($this->viewPath . str_replace('.', '/', $view) . '.php');
      }

      $content = $this->cache->stop();

      require($this->templatePath . $template . '.php');
    }
  }
