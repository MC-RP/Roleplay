package com.mcrp.roleplay.doors;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class DoorLocation implements Serializable {
	private static final long serialVersionUID = 912349L;
	private int x,y,z;

	public DoorLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public DoorLocation(Block b) {
		this(b.getX(),b.getY(),b.getZ());
	}
	
	public DoorLocation(Location b) {
		this((int)b.getX(),(int)b.getY(),(int)b.getZ());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public String getEncoded() {
		return x + "-" + y + "-" + z;
	}
	
	public String toString() {
		return "(X: " + x + ", Y: " + y + ", Z: " + z + ")";
		
	}
	
}
