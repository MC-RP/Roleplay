package com.mcrp.roleplay.economy;

import java.io.Serializable;

public class RentPeriod implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public int days,minutes,secounds;
	public int elapsedTime;
	
	public RentPeriod(int days, int minutes, int secounds, int elapsedTime) {
		super();
		this.days = days;
		this.minutes = minutes;
		this.secounds = secounds;
		this.elapsedTime = elapsedTime;
	}

	public void tick(int sec) {
		elapsedTime += sec;
	}
	
}
