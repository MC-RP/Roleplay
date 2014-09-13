package com.mcrp.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mcrp.roleplay.doors.DoorCommandExecutor;
import com.mcrp.roleplay.doors.DoorHandler;
import com.mcrp.roleplay.economy.EconCommandHandler;
import com.mcrp.roleplay.economy.EconomyHandler;

public class RolePlayPlugin extends JavaPlugin {
	
	
	public void onEnable() {
		this.getLogger().info("Roleplay has been enabled.");
		
		DoorHandler.getInstance().load(); // Load any existing doors.
		
		EconomyHandler.getInstance().setPlugin(this);
		EconomyHandler.getInstance().load((Player[]) getServer().getOnlinePlayers().toArray());
		
		Bukkit.getPluginManager().registerEvents(DoorHandler.getInstance().eventHandler, this); // Register door events
		
		// Door Commands
		this.getCommand("rpdoor").setExecutor(new DoorCommandExecutor());
		
		// Economy Commands
		EconCommandHandler ec = new EconCommandHandler();
		this.getCommand("econ").setExecutor(ec);
		this.getCommand("money").setExecutor(ec);
		this.getCommand("bal").setExecutor(ec);
		
	}
	
	public void onDisable() {
		DoorHandler.getInstance().save();
		EconomyHandler.getInstance().saveAll();
	}
	
}
