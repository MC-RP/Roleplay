package com.mcrp.roleplay.economy;

import org.bukkit.entity.Player;

public interface Rentable {
	public int getRentPrice();
	public int getRentPeriod();
	public int getElapsedTime();
	public void onPayFailure(Player p);
	public void onUnRent(Player p);
	public void onRent(Player p);
	public boolean isAvaliable();
}
