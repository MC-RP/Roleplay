package com.mcrp.roleplay.doors;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DoorEvents implements Listener {
	
	@EventHandler
	public void onDestroy(BlockBreakEvent e) {
		if(!DoorHandler.isValidDoor(e.getBlock().getType()))
			return;
		
		
		
	}
}
