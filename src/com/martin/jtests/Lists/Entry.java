package com.martin.jtests.Lists;

public class Entry {
	private String name;
	private int folder;
	private int register;
	private int page;
	
	public Entry(String name, int folder, int register, int page) {
		super();
		this.name = name;
		this.folder = folder;
		this.register = register;
		this.page = page;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFolder() {
		return folder;
	}
	public void setFolder(int folder) {
		this.folder = folder;
	}
	public int getRegister() {
		return register;
	}
	public void setRegister(int register) {
		this.register = register;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
