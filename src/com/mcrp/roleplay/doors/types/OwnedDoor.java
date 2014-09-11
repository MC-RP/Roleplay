package com.mcrp.roleplay.doors.types;


import java.io.Serializable;
import java.util.ArrayList;

import com.mcrp.roleplay.doors.CoOwnable;
import com.mcrp.roleplay.doors.Door;
import com.mcrp.roleplay.doors.DoorLocation;

public abstract class OwnedDoor extends Door implements CoOwnable, Serializable {


	private static final long serialVersionUID = 1L;

	private String ownerName;
	
	private String owner;
	private ArrayList<String> coowners;
	
	public OwnedDoor(DoorLocation b, String owner) {
		super(b);
		this.owner = owner;
		coowners = new ArrayList<String>();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public ArrayList<String> getCoowners() {
		return coowners;
	}

	public void addCoOwner(String uuid) {
		if(coowners.contains(uuid))
			return;
		coowners.add(uuid);
	}
	
	public void removeCoOwner(String uuid) {
		if(!coowners.contains(uuid))
			return;
		coowners.remove(uuid);
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void clearCoOwners() {
		this.coowners.clear();
	}
	
}
