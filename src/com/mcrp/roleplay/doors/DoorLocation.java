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

	public DoorLocation setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public DoorLocation setY(int y) {
		this.y = y;
		return this;
	}

	public int getZ() {
		return z;
	}

	public DoorLocation setZ(int z) {
		this.z = z;
		return this;
	}
	
	public String getEncoded() {
		return x + "-" + y + "-" + z;
	}
	
	public String toString() {
		return "(X: " + x + ", Y: " + y + ", Z: " + z + ")";
		
	}
	
}
