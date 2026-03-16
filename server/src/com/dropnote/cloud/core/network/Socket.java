package com.dropnote.cloud.core.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.UUID;

public class Socket
{
	private DatagramSocket socket;	

	private Address from;
	
	public Socket(Address from)
	{
		this.from = from;
		
		try
		{
			socket = new DatagramSocket(from.getPort());
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
	
	public void send(Packet packet)
	{
		if (packet.getItems().isEmpty())
			return;

		DatagramPacket d_Packet = null;
		String request = null;
		byte[] array = null;

		request = packet.getUniqueID().toString();

		for (Object item : packet.getItems())
			request += "§" + item.toString();

		array = request.getBytes();
		d_Packet = new DatagramPacket(array, array.length);
		d_Packet.setAddress(packet.getTo().getIp());
		d_Packet.setPort(packet.getTo().getPort());
		
		try
		{
			socket.send(d_Packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Packet receive()
	{
		byte[] array = new byte[10024];
		DatagramPacket d_Packet = new DatagramPacket(array, array.length);
		
		try
		{
			socket.receive(d_Packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		String[] items = new String(d_Packet.getData()).split("§");

		Packet packet = new Packet();
		for (String item : items)
			packet.put(item);			

		packet.setUniqueID(UUID.fromString(items[0]));

		return packet;
	}

	public Address getFrom()
	{
		return from;
	}

	public DatagramSocket getSocket()
	{
		return socket;
	}
}
