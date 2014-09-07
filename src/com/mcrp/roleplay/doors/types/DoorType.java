package com.mcrp.roleplay.doors.types;

public class DoorType {
	private int ID;
	private String NAMEID;
	private String NAME;
	
	public DoorType(int iD, String nAMEID, String nAME) {
		super();
		ID = iD;
		NAMEID = nAMEID;
		NAME = nAME;
	}

	public int getID() {
		return ID;
	}

	public String getNameID() {
		return NAMEID;
	}

	public String getName() {
		return NAME;
	}
}
