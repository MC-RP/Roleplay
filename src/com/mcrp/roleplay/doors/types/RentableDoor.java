package com.mcrp.roleplay.doors.types;

import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.DoorLocation;
import com.mcrp.roleplay.economy.Rentable;

public class RentableDoor extends OwnedDoor implements Rentable{

	public RentableDoor(DoorLocation b, int price, int  rentTime) {
		super(b, "None");
		this.price = price;
		this.rentPeriod = rentTime;
		this.elapsedTime = 0;
	}
	
	private static final long serialVersionUID = 4L;

	private int price;
	private int rentPeriod;
	private int elapsedTime;

	@Override
	public boolean canUnlock(Player p) {
		return false;
	}

	@Override
	public int getRentPrice() {
		return price;
	}

	@Override
	public void onPayFailure(Player p) {
		
	}

	@Override
	public void onUnRent(Player p) {
		
	}

	@Override
	public void onRent(Player p) {
		
	}

	@Override
	public int getRentPeriod() {
		return rentPeriod;
	}

	@Override
	public int getElapsedTime() {
		return elapsedTime;
	}
	
	@Override
	public boolean isAvaliable() {
		return this.getOwner().equalsIgnoreCase("None");
	}

}
