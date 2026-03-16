package com.dropnote.cloud.server;

import java.io.File;

import com.dropnote.cloud.core.events.EventDispatcher;
import com.dropnote.cloud.core.events.PacketReceivedEvent;
import com.dropnote.cloud.core.network.Address;
import com.dropnote.cloud.core.network.Packet;
import com.dropnote.cloud.core.network.Socket;
import com.dropnote.cloud.server.listeners.PacketReceived;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Cloud
{
	private EventDispatcher dispatcher;
	private Socket socket;

	public Cloud()
	{
		dispatcher = new EventDispatcher();
		socket = new Socket(new Address("localhost", 25565));
	}

	public void onEnable()
	{
		dispatcher.register(new PacketReceived());		

		File file = new File("/assets/test.png");
		ITesseract tesseract = new Tesseract();
		
		try
		{
			System.out.println(tesseract.doOCR(file));
		}
		catch (TesseractException e)
		{
			e.printStackTrace();
		}
		
		
		while(true)
		{			
			Packet packet = socket.receive();
			
			if(packet != null)
			{
				PacketReceivedEvent event = new PacketReceivedEvent();
				event.setPacket(packet);
				dispatcher.call(event);
			}
		}
	}

	public void onDisable()
	{
		
	}

	public static void main(String[] args)
	{
		Cloud cloud = new Cloud();
		cloud.onEnable();
	}

	public EventDispatcher getDispatcher()
	{
		return dispatcher;
	}

	public Socket getSocket()
	{
		return socket;
	}
}
