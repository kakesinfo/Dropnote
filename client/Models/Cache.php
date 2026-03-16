<?php

  namespace App\Models;

  class Cache
  {
    private $fileName;
    private $fileLifetime;
    private $fileExist = false;

    private $cacheContent = "";

    private $isValid;

    public function __construct($fileName, $cacheExpire)
    {
      $this->fileName = "Cache/" . $fileName . ".cache";

      $this->fileExist = file_exists($this->fileName);

      $this->fileLifetime = $this->fileExist ? (time() - filemtime($this->fileName)) / 60 : $cacheExpire;

      $this->isValid = $this->fileLifetime < $cacheExpire;
    }

    public function start()
    {
      ob_start();
      return $this->isValid;
    }

    public function stop()
    {
      if(!$this->fileExist)
      {
        $this->cacheContent = ob_get_contents();
        $this->save();
      }
      if($this->isValid)
      {
        ob_clean();
        $this->cacheContent = file_get_contents($this->fileName);
      }
      else
      {
        $this->cacheContent = ob_get_clean();
        $this->save();
      }
      return $this->cacheContent;
    }

    private function save()
    {
      file_put_contents($this->fileName, $this->cacheContent);
    }

    private function delete()
    {
      if(file_exists($this->fileName))
      {
        unlink($this->fileName);
      }
    }

  }
