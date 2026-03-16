package com.dropnote.cloud.core.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Address
{
	private InetAddress ip;
	private int port;
	
	public Address(String ip, int port)
	{
		try
		{
			this.ip = InetAddress.getByName(ip);
			this.port = port;
		}
		catch (UnknownHostException e)
		{
			
		}
	}

	public InetAddress getIp()
	{
		return ip;
	}

	public int getPort()
	{
		return port;
	}
	
}
