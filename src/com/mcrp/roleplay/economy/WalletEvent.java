package com.mcrp.roleplay.economy;

import org.bukkit.entity.Player;

public class WalletEvent extends EconomyEvent{

	public WalletEvent(Player p, int beforeAmount, int afterAmonunt) {
		super(p, beforeAmount, afterAmonunt);
	}

}
