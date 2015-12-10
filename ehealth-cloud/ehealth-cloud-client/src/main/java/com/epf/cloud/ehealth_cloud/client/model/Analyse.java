package com.epf.cloud.ehealth_cloud.client.model;

public class Analyse {
	public Analyse()
	{
		this(0, false);
	}
	
	public Analyse(int usrAge, boolean usrSex) {
		super();
		this.usrAge = usrAge;
		this.usrSex = usrSex;		
	}
	
	private int usrAge;
	private boolean usrSex;
	
	public int getUsrAge() {
		return usrAge;
	}
	public void setUsrAge(int usrAge) {
		this.usrAge = usrAge;
	}
	
	
	public boolean isUsrSex() {
		return usrSex;
	}
	public void setUsrSex(boolean usrSex) {
		this.usrSex = usrSex;
	}
	
}
