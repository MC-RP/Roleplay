package com.mcrp.roleplay.economy;

import java.io.Serializable;

import org.bukkit.entity.Player;

public interface Buyable extends Serializable{
	public int getBuyPrice();
	public int getSellPrice();
	public void onBuy(Player p);
	public void onSell(Player p);
}
