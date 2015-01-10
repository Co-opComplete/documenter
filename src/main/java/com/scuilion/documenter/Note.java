package com.scuilion.documenter;

public class Note {
	public Note(String key, int priority) {
		super();
		this.key = key;
		this.priority = priority;
	}

	private String key;

	private int priority;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
