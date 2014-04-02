package com.martin.jtests.serializing;

public class Action {
	
	private Commands command;
	private String data;
	
	public Action(Commands command, String data) {
		super();
		this.command = command;
		this.data = data;
	}

	public Commands getCommand() {
		return command;
	}

	public String getData() {
		return data;
	}
	
}
