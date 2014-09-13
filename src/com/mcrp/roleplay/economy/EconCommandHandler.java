package com.mcrp.roleplay.economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconCommandHandler implements CommandExecutor {

	private static final String TAG = ChatColor.RESET + "" + ChatColor.BOLD + "[" + ChatColor.RESET + ChatColor.DARK_GREEN + "Econ" + ChatColor.RESET + "" + ChatColor.BOLD + "]";  
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("bal")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This is a player command.");
				return true;
			}
			
				showInfo((Player)sender);
		}
		
		if(label.equalsIgnoreCase("econ") ||  label.equalsIgnoreCase("money")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This is a player command.");
				return true;
			}
			if(args.length < 1)
				showInfo((Player)sender);
			
			return true;
		}
		
		return false;
	}
	
	private static void showInfo(Player p) {
		EconomyPlayer e = EconomyHandler.getInstance().getPlayer(p);
		p.sendMessage(TAG + ChatColor.GREEN + " Wallet Balance: " + e.getWallet() + "$ Bank balance: " + e.getAccount() + "$");
	}

}
