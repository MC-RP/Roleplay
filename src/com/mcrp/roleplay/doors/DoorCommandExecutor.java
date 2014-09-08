package com.mcrp.roleplay.doors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.types.ApartmentDoor;
import com.mcrp.roleplay.doors.types.PermissionDoor;

public class DoorCommandExecutor implements CommandExecutor {

	private final String TAG = ChatColor.RESET +"[" + ChatColor.RED + "RP" + ChatColor.RESET + "]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!label.equalsIgnoreCase("rpdoor"))
			return false;
		
		if(args.length < 1 || args == null) {
			sender.sendMessage(TAG + ChatColor.AQUA + " Roleplay doors extenstion for RolePlay.");
			return true;
		}
		
		// At least 1 argument.
		
		if(args[0].equalsIgnoreCase("create")) {
			if(args.length < 3) {
				sender.sendMessage(TAG + " Need atleast two more args");
				return true;
			}
			if(!(sender instanceof Player)) {
				sender.sendMessage(TAG + " This is a player command bro :P");
				return true;
			}
			
			Player p = (Player) sender;
				
			Block b = getTargetBlock(p);
			
			if(b.getType().equals(Material.AIR)) {
				sender.sendMessage(TAG + ChatColor.RED + " You are not in a line of sight of any blocks.");
				return true;
			}
			
			if(!DoorHandler.isValidDoor(b.getType())) {
				sender.sendMessage(TAG + ChatColor.RED + " " + b.getType().toString() + " is no door block.");
				return true;
			}
			
		
			if(args[1].equalsIgnoreCase("permission")) {
				Door d = new PermissionDoor(new DoorLocation(b), args[2]);
				DoorHandler.getInstance().addNewDoor(d);
				p.sendMessage(TAG + ChatColor.GREEN + " Succesfully created a permission door. Permission: " + args[2]);
				return true;
			}
				
			if(args[1].equalsIgnoreCase("apartement")) {
				int price;
				try {
					price = Integer.parseInt(args[2]);
				}
				catch(NumberFormatException e) {
					p.sendMessage(TAG + ChatColor.RED + " The price most be in number format without decimals!");
					return true;
				}
				
				Door d = new ApartmentDoor(new DoorLocation(b), "None", price);
				DoorHandler.getInstance().addNewDoor(d);
				p.sendMessage(TAG + ChatColor.GREEN + " Succesfully created a apartment door. Price: " + args[2]);
				return true;
			}	
				
		}
		
		if(args[1].equalsIgnoreCase("remove")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(TAG + " This is a player command bro :P");
				return true;
			}
			
			Player p = (Player) sender;
				
			Block b = getTargetBlock(p);
			
			if(b.getType().equals(Material.AIR)) {
				sender.sendMessage(TAG + ChatColor.RED + " You are not in a line of sight of any blocks.");
				return true;
			}
			
			if(!DoorHandler.isValidDoor(b.getType())) {
				sender.sendMessage(TAG + ChatColor.RED + " " + b.getType().toString() + " is no door block.");
				return true;
			}
			
			
			if(!DoorHandler.getInstance().isDoorThere(new DoorLocation(b))) {
				sender.sendMessage(TAG + ChatColor.RED + " The door is a door but not a rpdoor :/");
				return true;
			}
			
			DoorHandler.getInstance().removeDoor(DoorHandler.getInstance().getDoor(new DoorLocation(b)).getPos().getEncoded());
			sender.sendMessage(TAG + ChatColor.RED + " Removed" + ChatColor.GREEN + " the selected door successfully!");
			
		}
			
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public static Block getTargetBlock(Player player) {
	     Block target = player.getWorld().getBlockAt(player.getLocation().add(player.getLocation().getDirection()));
	       if(target.getType() != Material.AIR)
	    	   return target;
	     	for (Block b : player.getLineOfSight(null, 10)) {
	            if (!b.getType().equals(Material.AIR)) { target = b; break; }
	        }
	     return target;
	}
	
}
