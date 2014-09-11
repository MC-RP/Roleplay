package com.mcrp.roleplay.economy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EconomyHandler implements Listener{
	private HashMap<String, Long> loadedAccounts;
	private HashMap<String, Integer> loadedWallets;
	private final String FileName = "RpEconomy.mcrp";
	
	private static EconomyHandler instance;
	public static EconomyHandler getInstance() {
		if(instance == null)
			instance = new EconomyHandler();
		return instance;
	}
	
	public void save() {
		save(FileName);
	}
	
	public void save(String filename) {
		try
        {
               FileOutputStream fos =
                  new FileOutputStream(filename);
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               oos.writeObject(loadedWallets);
               oos.writeObject(loadedAccounts);
               oos.close();
               fos.close();
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }
	}
	
	@SuppressWarnings("unchecked")
	public void load() {
		try
	      {
	         FileInputStream fis = new FileInputStream(FileName);
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         loadedWallets = (HashMap<String, Integer>) ois.readObject();
	         loadedAccounts = (HashMap<String, Long>) ois.readObject();
	         ois.close();
	         fis.close();
	      }catch(IOException ioe)
	      {
	         ioe.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         return;
	      }
	}
	
	public EconomyHandler() {
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
