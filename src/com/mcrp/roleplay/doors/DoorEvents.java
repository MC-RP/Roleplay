package com.mcrp.roleplay.doors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

public class DoorEvents implements Listener {
	
	private final String TAG = ChatColor.RESET +"[" + ChatColor.RED + "RP" + ChatColor.RESET + "]";
	
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDestroy(BlockBreakEvent e) {
		if(e.isCancelled())
			return;
		if(!DoorHandler.isValidDoor(e.getBlock().getType()))
			return;
		if(DoorHandler.getInstance().getDoor(new DoorLocation(e.getBlock())) == null) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Delete the rp door with /rpdoor remove first!");
			return;
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH) 
	public void playerRightClick(PlayerInteractEvent e) { // Only on right click a door.
		if(!e.hasBlock())
			return;
		if(!DoorHandler.isValidDoor(e.getClickedBlock().getType()))
			return;
		
		Door d = DoorHandler.getInstance().getDoor(new DoorLocation(e.getClickedBlock()));
		if(d == null) {
			return;
		}
		
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {		
			if(d.isLocked()) {
				e.setCancelled(true);
				return;
			}
			else
				if(e.getClickedBlock().getType().equals(Material.IRON_DOOR_BLOCK) || e.getClickedBlock().getType().equals(Material.IRON_DOOR)) {
					if(d.isLocked())
						return;
					
					BlockState state = getRealDoor(e.getClickedBlock()).getState();
					MaterialData data = state.getData();
					if(!(data instanceof Openable))
						return;
					Openable open = (Openable) data;
					open.setOpen(switchBool(open.isOpen()));
					
					return;
				}
			return;
		}
		
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if(!e.getPlayer().isSneaking())
				d.showMenu(e.getPlayer());
			else {
				if(d.canUnlock(e.getPlayer()))
					d.switchLock();
				else
					e.getPlayer().sendMessage(TAG + ChatColor.RED + " This door is not yours to open.");
				if(d.isLocked())
					e.getPlayer().sendMessage(TAG + ChatColor.GREEN + " You Unlocked this door.");
				else
					e.getPlayer().sendMessage(TAG + ChatColor.GREEN + " You " + ChatColor.RED + "Locked" + ChatColor.GREEN +" this door.");
			}
			
		}
		
	}

	public static Block getRealDoor(Block b) {
		Block under = b.getWorld().getBlockAt(b.getX(), b.getY() - 1, b.getZ());
			if(under.getType() == Material.IRON_DOOR_BLOCK || under.getType() == Material.IRON_BLOCK) {
				return under;
			}
		return b;
	}
	
	public static boolean switchBool(boolean i) {
		if(i)
			return false;
		else
			return true;
	}
	
}
