package com.mcrp.roleplay.doors;

import java.io.Serializable;

import org.bukkit.entity.Player;

public abstract class Door implements Serializable {
	
	private static final long serialVersionUID = 0L;
	private String id;
	
	public Door(DoorLocation pos) {
		id = pos.getEncoded();
	}
	
	
	public boolean canUnlock(Player p) {
		return true;
	}
}
