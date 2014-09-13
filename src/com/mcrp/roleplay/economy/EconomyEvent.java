package com.mcrp.roleplay.economy;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class EconomyEvent extends PlayerEvent {
    
	private static final HandlerList handlers = new HandlerList();

	private long beforeAmount;
	private long afterAmonunt;
	
	public EconomyEvent(Player p, long beforeAmount, long afterAmonunt) {
		super(p);
		this.beforeAmount = beforeAmount;
		this.afterAmonunt = afterAmonunt;
	}

	public long beforeSaldo() {
		return beforeAmount;
	}

	public long afterSaldo() {
		return afterAmonunt;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
