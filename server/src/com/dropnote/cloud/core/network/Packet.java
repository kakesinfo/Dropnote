package com.dropnote.cloud.core.network;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Packet
{
	private UUID uniqueID;
	private Address to;
	
	private List<Object> items;
	
	public Packet()
	{
		this(null);
	}
	
	public Packet(Address to)
	{
		this.to = to;
		
		uniqueID = UUID.randomUUID();
		items = new ArrayList<>();
	}

	public void put(String item)
	{
		items.add(item);
	}
	
	public void put(int item)
	{
		items.add(item);
	}
	
	public void put(double item)
	{
		items.add(item);
	}
	
	public void put(float item)
	{
		items.add(item);
	}
	
	public void put(long item)
	{
		items.add(item);
	}
	
	public void put(byte item)
	{
		items.add(item);
	}
	
	public Object get(int index)
	{
		return items.get(index);
	}

	public UUID getUniqueID()
	{
		return uniqueID;
	}

	public void setUniqueID(UUID uniqueID)
	{
		this.uniqueID = uniqueID;
	}

	public Address getTo()
	{
		return to;
	}

	public List<Object> getItems()
	{
		return items;
	}
}
