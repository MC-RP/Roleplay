package com.mcrp.roleplay.doors;

import java.io.Serializable;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.types.DoorType;

public abstract class Door implements Serializable {
	
	private static final long serialVersionUID = 0L;
	
	private DoorLocation pos;
	private boolean isLocked;
	
	public Door(DoorLocation pos) {
		this.pos = pos;
		isLocked = false;
	}
	
	public String getKey() {
		return pos.getEncoded();
		
	}
	
	public void switchLock() {
		if(isLocked)
			isLocked = false;
		else
			isLocked = true;
	}
	
	public boolean isLocked() {
		return isLocked;
	}

	public abstract boolean canUnlock(Player p);

	
	public abstract DoorType getType();
	
	public DoorLocation getPos() {
		return pos;
	}
	
	public void showMenu(Player p) {
		p.sendMessage(ChatColor.RESET +"[" + ChatColor.RED + "RP" + ChatColor.RESET + "]" + ChatColor.AQUA + " A rp door, no other info :S.");
	}
	
}
