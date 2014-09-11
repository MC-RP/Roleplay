package com.mcrp.roleplay.doors.types;

import java.io.Serializable;

import org.bukkit.entity.Player;

import com.mcrp.roleplay.doors.DoorLocation;
import com.mcrp.roleplay.economy.Buyable;

public class ApartmentDoor extends OwnedDoor implements Buyable, Serializable {
	
	private static final long serialVersionUID = 2L;
	private int price;
	
	public ApartmentDoor(DoorLocation b, String owner, int price) {
		super(b, owner);
		this.price = price;
	
	}

	public boolean canUnlock(Player p) {
		if(getOwner() == p.getUniqueId().toString()) {
			setOwnerName(p.getDisplayName());
			return true;
		}
		
		return this.getCoowners().contains(p.getUniqueId().toString());
	}
	
	@Override
	public void onBuy(Player p) {
		setOwner(p.getUniqueId().toString());
		setOwnerName(p.getDisplayName());
	}

	@Override
	public void onSell(Player p) {
		setOwner("None");
		setOwnerName("None");
		clearCoOwners();
	}

	@Override
	public int getBuyPrice() {
		return price;
	}

	@Override
	public int getSellPrice() {
		return (int) (price * 0.8);
	}
}
