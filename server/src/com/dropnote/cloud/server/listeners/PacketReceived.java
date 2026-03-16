package com.dropnote.cloud.server.listeners;

import com.dropnote.cloud.core.events.EventHandler;
import com.dropnote.cloud.core.events.Listener;
import com.dropnote.cloud.core.events.PacketReceivedEvent;

public class PacketReceived implements Listener<PacketReceivedEvent>
{
	@EventHandler
	public void onEvent(PacketReceivedEvent event)
	{
		event.getPacket().getItems().forEach((item) -> System.out.println(item));
	}	
}
