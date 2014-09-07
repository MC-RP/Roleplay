package com.mcrp.roleplay.economy;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EconomyMain implements Listener{
	private HashMap<String, Long> loadedAccounts;
	private HashMap<String, Integer> loadedWallets;
	
	private static EconomyMain instance;
	public static EconomyMain getInstance() {
		if(instance == null)
			instance = new EconomyMain();
		return instance;
	}
	
	public EconomyMain() {
		loadedAccounts = new HashMap<String, Long>();
		loadedWallets = new HashMap<String, Integer>();
	}
	
	
	public int getWallet(Player p) {
		if(!loadedWallets.containsKey(p.getUniqueId().toString())) {
			Bukkit.getLogger().warning("[RP] PLAYER " + p.getName() + " DID NOT EXIST IN WALLETDB.");
			return 0;
		}
		
		return loadedWallets.get(p.getUniqueId().toString());
	}
	public long getBank(Player p) {
		if(!loadedAccounts.containsKey(p.getUniqueId().toString())) {
			Bukkit.getLogger().warning("[RP] PLAYER " + p.getName() + " DID NOT EXIST IN WALLETDB.");
			return 0;
		}
		
		return loadedAccounts.get(p.getUniqueId().toString());
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		//TODO: Load or Create Account and wallet.
	}
	
	
	@EventHandler
	public void onLogout(PlayerQuitEvent e) {
		//TODO: Save and remove Account and wallet.
	}
	
}
