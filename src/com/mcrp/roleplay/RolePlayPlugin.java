package com.mcrp.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.mcrp.roleplay.doors.DoorCommandExecutor;
import com.mcrp.roleplay.doors.DoorHandler;
import com.mcrp.roleplay.economy.EconomyHandler;

public class RolePlayPlugin extends JavaPlugin {
	
	
	public void onEnable() {
		this.getLogger().info("Roleplay has been enabled.");
		
		DoorHandler.getInstance().load(); // Load any existing doors.
		EconomyHandler.getInstance().load();
		
		Bukkit.getPluginManager().registerEvents(DoorHandler.getInstance().eventHandler, this); // Register door events
	
		this.getCommand("rpdoor").setExecutor(new DoorCommandExecutor());
	}
	
	public void onDisable() {
		DoorHandler.getInstance().save();
		EconomyHandler.getInstance().save();
	}
	
}
