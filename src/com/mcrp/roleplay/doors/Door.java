package com.mcrp.roleplay.doors;

import java.io.Serializable;

import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.types.DoorType;

public abstract class Door implements Serializable {
	
	private static final long serialVersionUID = 0L;
	
	private DoorLocation pos;
	
	
	public Door(DoorLocation pos) {
		this.pos = pos;
	}
	
	public String getKey() {
		return pos.getEncoded();
		
	}

	public abstract boolean canUnlock(Player p);

	
	public abstract DoorType getType();
	
}
