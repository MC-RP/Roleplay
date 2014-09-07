package com.mcrp.roleplay.doors.types;

import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.DoorLocation;
import com.mcrp.roleplay.economy.Buyable;

public class ApartmentDoor extends OwnedDoor implements Buyable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int price;
	
	public ApartmentDoor(DoorLocation b, String owner, int price) {
		super(b, owner);
		this.price = price;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void buy(Player p) {
				
	}

	@Override
	public void sell(Player p) {
		
	}

}
