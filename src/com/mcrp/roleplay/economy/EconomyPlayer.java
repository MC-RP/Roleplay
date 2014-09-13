package com.mcrp.roleplay.economy;

import java.io.Serializable;

public class EconomyPlayer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String uuid;
	
	private int wallet;
	private long account;
	
	
	public EconomyPlayer(String p, int w, long a) {
		uuid = p;
		wallet = w;
		account = a;
	}

	public String getUUID() {
		return uuid;
	}


	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}
	
	
	
}
