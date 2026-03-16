package com.dropnote.cloud.core.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class ImageRecognition
{
	private ITesseract tesseract;

	public ImageRecognition()
	{
		tesseract = new Tesseract();
	}

	public int[] process(byte[] image)
	{
		return null;
	}
}
