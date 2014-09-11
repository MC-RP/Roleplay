package com.mcrp.roleplay.doors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class DoorHandler {
	
	public DoorEvents eventHandler;
	
	private static Material[] VALID_DOORS_BLOCKS = new Material[] {Material.WOODEN_DOOR, Material.WOOD_DOOR, Material.IRON_DOOR, Material.IRON_DOOR_BLOCK, Material.FENCE_GATE, Material.TRAP_DOOR};
	
	private HashMap<String, Door> doors; // Key is made out of the pos like this: x-y-z for example 122-62-720.
	
	private static DoorHandler instance;
	
	private static final String filename = "RpDoors.mcrp";
	
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
		save(filename);
	}
	
	public void save(String filename) {
		if(doors.size() == 0) {
			 Bukkit.getLogger().info("[RP] No doors to save continuing..");
			return;
		}
		 try
        {
			   Bukkit.getLogger().info("[RP] Saving doors...");
			   long start = System.nanoTime();
               FileOutputStream fos = new FileOutputStream(filename);
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               
               oos.writeObject(doors.values().toArray());
               
               oos.close();
               fos.close();
               Bukkit.getLogger().info("[RP] " + doors.size() +" doors saved in " + (System.nanoTime() - start) + " ns");
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }
	}
	
	@SuppressWarnings("resource")
	public void load() {
		File f = new File(filename);
		if(!f.exists())
			return;
		
		
		try
	      {
	         FileInputStream fileIn = new FileInputStream(f);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         Object[] objs = (Object[]) in.readObject();
	       
	         if(objs.length == 0)
	        	 return;
	         
	         in.close();
	         fileIn.close();
	         
	         Door[] a = Arrays.copyOf(objs, objs.length, Door[].class);
	         
	         for(Door d : a) addNewDoor(d);
	         
	         
	      }catch(IOException i)
	      {
	    	 Bukkit.getLogger().info("ERROR IO EXCEPTION CASTED AT DoorHandler.load");
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
	
	public Door fromKey(String k) {
		if(doors.containsKey(k))
			return doors.get(k);
		
		
		return null;
		
	}
	
	public boolean validateKey(String k) {
		return doors.containsKey(k);
	}
	
	public String getDoorKey(DoorLocation l) {
		
		int x = l.getX(), y = l.getY(), z = l.getZ();
		
		if(doors.containsKey(l.getEncoded()))
			return l.getEncoded();
		
		if(doors.containsKey(createKey(x,y + 1,z)))
			return createKey(x,y + 1,z);
		
		if(doors.containsKey(createKey(x,y - 1,z)))
			return createKey(x,y + 1,z);
		
				
		
		return "NULL";
	}
	
	public Door getDoor(DoorLocation b) {
		
		if(doors.containsKey(b.getEncoded()))
			return doors.get(b.getEncoded());
		
		if(doors.containsKey(b.setY(b.getY() + 1).getEncoded()))
			return doors.get(b.setY(b.getY() + 1).getEncoded());
		
		if(doors.containsKey(b.setY(b.getY() - 2).getEncoded()))
			return doors.get(b.setY(b.getY() - 2).getEncoded());
		
		return null;
	}
	
	public boolean isDoorThere(DoorLocation l) {
		return getDoor(l) == null;
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

	public void removeDoor(String encoded) {
		if(!doors.containsKey(encoded))
			return;
		doors.remove(encoded);
	}
	
	public static String createKey(int x, int y, int z) {
		return x + "-" + y + "-" + z;
	}
	
}
