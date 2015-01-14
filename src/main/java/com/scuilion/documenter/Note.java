package com.scuilion.documenter;

public class Note {
	private String key;
	private int priority;
	private String className;

	public Note(String key, int priority, String className) {
		super();
		this.key = key;
		this.priority = priority;
		this.className = className;
	}

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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
