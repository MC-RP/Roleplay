package com.mcrp.roleplay.economy;

public interface Rentable {
	public void getRentPrice();
	public void onPayFailure();
	public void onUnRent();
	public void onRent();
}
