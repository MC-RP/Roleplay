package com.mcrp.roleplay.doors;

import java.io.Serializable;
import java.util.ArrayList;

public interface CoOwnable extends Serializable{
	public void removeCoOwner(String uuid);
	public void addCoOwner(String uuid);
	public ArrayList<String> getCoowners();
}
