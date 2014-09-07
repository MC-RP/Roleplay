package com.mcrp.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.mcrp.roleplay.doors.DoorHandler;

public class RolePlayPlugin extends JavaPlugin {
	
	
	public void onEnable() {
		this.getLogger().info("Roleplay has been enabled.");
		
		DoorHandler.getInstance().load(); // Load any existing doors.
		
		Bukkit.getPluginManager().registerEvents(DoorHandler.getInstance().eventHandler, this); // Register door events
	}
	
	public void onDisable() {
		
	}
	
}
