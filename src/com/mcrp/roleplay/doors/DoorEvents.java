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

public class DoorEvents implements Listener {
	
	private final String TAG = ChatColor.RESET +"[" + ChatColor.RED + "RP" + ChatColor.RESET + "]";
	
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onDestroy(BlockBreakEvent e) {
			if(!DoorHandler.isValidDoor(e.getBlock().getType()))
				return;
		
			String finalKey = DoorHandler.getInstance().getDoorKey(new DoorLocation(e.getBlock()));
			
			if(finalKey.equals("NULL"))
				return;

			
			e.setCancelled(true);
			if(e.getPlayer().isOp()) 
				e.getPlayer().sendMessage(ChatColor.RED + "Delete the rp door with /rpdoor remove first!");
			return;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST) 
	public void playerClickBlock(PlayerInteractEvent e) {
		if(!e.hasBlock())
			return;
		if(!DoorHandler.isValidDoor(e.getClickedBlock().getType()))
			return;
		
		String finalKey = DoorHandler.getInstance().getDoorKey(new DoorLocation(e.getClickedBlock()));
		
		if(finalKey.equals("NULL"))
			return;
		
		Door d = DoorHandler.getInstance().fromKey(finalKey);
				
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {		
			if(d.isLocked()) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(TAG + ChatColor.RED + "This door is currently locked!");
				return;
			}
			else
				if(e.getClickedBlock().getType().equals(Material.IRON_DOOR_BLOCK) || e.getClickedBlock().getType().equals(Material.IRON_DOOR)) {
					if(d.isLocked())
						return;
					
					BlockState state = getRealDoor(e.getClickedBlock()).getState();
					
					
					org.bukkit.material.Door door = (org.bukkit.material.Door) state;
					door.setOpen(switchBool(door.isOpen()));
					
					e.getPlayer().getPlayer().sendMessage(door.isOpen() + "");
					
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
				else {
					e.getPlayer().sendMessage(TAG + ChatColor.RED + " This door is not yours to open.");
					return;
				}
					
				if(d.isLocked())
					e.getPlayer().sendMessage(TAG + ChatColor.GREEN + " You " + ChatColor.RED + "Locked" + ChatColor.GREEN +" this door.");
				else
					e.getPlayer().sendMessage(TAG + ChatColor.GREEN + " You Unlocked this door.");
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
