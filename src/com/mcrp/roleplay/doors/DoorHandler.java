package com.mcrp.roleplay.doors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class DoorHandler {
	
	public DoorEvents eventHandler;
	
	private static Material[] VALID_DOORS_BLOCKS = new Material[] {Material.WOODEN_DOOR, Material.WOOD_DOOR, Material.IRON_DOOR, Material.IRON_DOOR_BLOCK, Material.FENCE_GATE, Material.TRAP_DOOR};
	
	private HashMap<String, Door> doors; // Key is made out of the pos like this: x-y-z for example 122-62-720.
	
	private static DoorHandler instance;
	
	private static final String filename = "RP-DOORS.MCRPDOORS";
	
	public DoorHandler() {
		doors = new HashMap<String, Door>();
		eventHandler = new DoorEvents();
	}
	
	public void addNewDoor(Door d) {
		
		if(doors.containsKey(d.getKey())) {
			Bukkit.getLogger().warning("[RP] The plugin thried to add the same door twice :/ ");
			return;
		}
		
		doors.put(d.getKey(), d);
	}
	
	
	public void save() {
		 try
        {
			   Bukkit.getLogger().info("[RP] Saving doors...");
			   long start = System.nanoTime();
               FileOutputStream fos = new FileOutputStream(filename);
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               
               oos.writeObject(((Door[])doors.values().toArray()));
               
               oos.close();
               fos.close();
               Bukkit.getLogger().info("[RP] " + doors.size() +" doors saved in " + (System.nanoTime() - start) + " ns");
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }
	}
	
	public void load() {
		
		File f = new File(filename);
		if(!f.exists())
			return;
		
		
		try
	      {
	         FileInputStream fileIn = new FileInputStream(f);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         Door[] doorarray = (Door[]) in.readObject();
	         in.close();
	         fileIn.close();
	         
	         if(doorarray.length == 0)
	        	 return;
	         
	         for(Door d : doorarray) addNewDoor(d);
	         
	         
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	    	  Bukkit.getLogger().warning("Unable to find targeted class :S");
	         c.printStackTrace();
	         return;
	      }
	}
	
	public boolean isRPDoor(Block b) {
		
		return doors.containsKey(keyBlock(b));
		
	}
	
	public static String keyBlock(Block b) {
		return b.getX() + "-" + b.getY() + "-" + b.getZ();
	}
	
	public Door getDoorExact(Block b) {
		return doors.get(keyBlock(b));
	}
	
	public static DoorHandler getInstance() {
		if(instance == null)
			instance = new DoorHandler();
		return instance;
	}
	
	public static boolean isValidDoor(Material m) {
		for(Material validmaterial : VALID_DOORS_BLOCKS) {
			if(validmaterial.equals(m))
				return true;
		}
		
		return false;
	}
	
}
