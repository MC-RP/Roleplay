package com.mcrp.roleplay.doors.types;

import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.DoorLocation;
import com.mcrp.roleplay.economy.Buyable;

public class ApartmentRentableDoor extends RentableDoor implements Buyable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isAvaliableForRent;
	
	public ApartmentRentableDoor(DoorLocation b, String owner) {
		super(b, owner);
		// TODO Auto-
	}

	@Override
	public int getBuyPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSellPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onBuy(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSell(Player p) {
		// TODO Auto-generated method stub
		
	}
}
