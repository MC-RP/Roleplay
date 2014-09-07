package com.mcrp.roleplay.economy;

import org.bukkit.entity.Player;

public interface Buyable {
	public int getPrice();
	public void buy(Player p);
	public void sell(Player p);
}
