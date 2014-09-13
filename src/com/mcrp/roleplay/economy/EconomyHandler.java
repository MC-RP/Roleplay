package com.mcrp.roleplay.economy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class EconomyHandler implements Listener {
	
	private HashMap<String, EconomyPlayer> loadedPlayers;
	
	private final static int startWallet = 250;
	private final static long startBank = 10000;
	
	
	private Plugin pl;
	
	private final String savePath = "playerdata" + File.pathSeparator;
	
	private static EconomyHandler instance;
	public static EconomyHandler getInstance() {
		if(instance == null)
			instance = new EconomyHandler();
		return instance;
	}
	
	public void saveAll() {
		for(EconomyPlayer p : loadedPlayers.values()) {
			save(p);
		}
	}
	
	public void save(EconomyPlayer p) {
		try
        {
               FileOutputStream fos = new FileOutputStream(savePath + p.getUUID()+ ".econ");
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               oos.writeObject(p);
               oos.close();
               fos.close();
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }
	}
	
	public void load(Player... players) {
		
		for(Player p : players) {
				load(p.getUniqueId().toString());
		}
	}
	
	public EconomyPlayer load(String player) {
		File f = new File(savePath + player + ".econ");
		if(f.exists()) {
			loadedPlayers.put(player, new EconomyPlayer(player, startWallet, startBank));
			return null;
		}
		try
	      {
	         FileInputStream fis = new FileInputStream(savePath + player + ".econ");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         EconomyPlayer p = (EconomyPlayer) ois.readObject();
	         ois.close();
	         fis.close();
	         loadedPlayers.put(player, p);
	         return p;
	      }catch(IOException ioe)
	      {
	         ioe.printStackTrace();
	         return null;
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	      }
		return null;
	}
	
	public EconomyHandler() {
		loadedPlayers = new HashMap<String, EconomyPlayer>();
	}
	
	public EconomyPlayer getPlayer(Player p) {
		return getPlayer(p.getUniqueId().toString());
	}
	
	public EconomyPlayer getPlayer(String p) {
		if(!loadedPlayers.containsKey(p))
			return null;
		return loadedPlayers.get(p);
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		this.load(e.getPlayer());
	}
	
	@EventHandler
	public void onLogout(PlayerQuitEvent e) {
		this.save(getPlayer(e.getPlayer()));
	}

	public void setPlugin(Plugin pl) {
		this.pl = pl;
	}
	
	public void addAmtBank(String a, int amt) {
		pl.getServer().getPluginManager().callEvent(new BankEvent(pl.getServer().getPlayer(UUID.fromString(a)), getPlayer(a).getAccount(), getPlayer(a).getAccount() + amt));
		getPlayer(a).setAccount(getPlayer(a).getAccount() + amt);
	}
	
	public void subAmtBank(String a, int amt) {
		addAmtBank(a,amt*-1);
	}
	
	public boolean bankTransfer(String a, String b, int amt) {
		if(getPlayer(a).getWallet() < amt)
			return false;
		
		subAmtBank(a, amt);
		addAmtBank(a, amt);
		return true;
	}
	
}
