package com.epf.cloud.ehealth_cloud.common;

public class ClientData {
		
	public ClientData(String data, int size) {
		super();
		this.data 		= data;
		this.size 		= size;
	}	
	
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	public ClientData() {
		// TODO Auto-generated constructor stub
	}
	
	private String data;
	private int size;	
}
