package com.mcrp.roleplay.doors.types;

import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.Door;
import com.mcrp.roleplay.doors.DoorLocation;

public class JobDoor extends Door {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;

	public JobDoor(DoorLocation pos, String Job) {
		super(pos);
	}

	@Override
	public boolean canUnlock(Player p) {
		return true;
	}

}
