package com.mcrp.roleplay.doors.types;

import java.io.Serializable;

import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.Door;
import com.mcrp.roleplay.doors.DoorHandler;
import com.mcrp.roleplay.doors.DoorLocation;

public class SubDoor extends Door implements Serializable {

	private static final long serialVersionUID = 10L;
	
	private String mainPos;
	
	public SubDoor(DoorLocation pos, String m) {
		super(pos);
		mainPos = m;
	}
	
	public String getMainKey() {
		return mainPos;
	}

	@Override
	public boolean canUnlock(Player p) {
		return DoorHandler.getInstance().fromKey(mainPos).canUnlock(p);
	}
	
	
	

}
