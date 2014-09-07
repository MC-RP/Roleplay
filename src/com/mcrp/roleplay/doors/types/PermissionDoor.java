package com.mcrp.roleplay.doors.types;

import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.Door;
import com.mcrp.roleplay.doors.DoorLocation;

public class PermissionDoor extends Door{
	private static final long serialVersionUID = 6L;

	private String permission;
	
	public PermissionDoor(DoorLocation pos, String permissionNode) {
		super(pos);
		permission = permissionNode;
	}

	public boolean canUnlock(Player p) {
		return p.hasPermission(permission);
	}
	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public DoorType getType() {
		return new DoorType(6,"PERMISSION_DOOR", "Permission Door");
	}

}
