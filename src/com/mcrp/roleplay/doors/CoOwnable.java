package com.mcrp.roleplay.doors;

import java.util.ArrayList;

public interface CoOwnable {
	public void removeCoOwner(String uuid);
	public void addCoOwner(String uuid);
	public ArrayList<String> getCoowners();
}
