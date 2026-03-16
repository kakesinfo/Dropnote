package com.dropnote.cloud.core.events;

import com.dropnote.cloud.core.network.Packet;

public class PacketReceivedEvent extends Event
{
	private Packet packet;	

	public void setPacket(Packet packet)
	{
		this.packet = packet;
	}

	public Packet getPacket()
	{
		return packet;
	}
}
