package com.scuilion.documenter;

import javax.lang.model.element.ElementKind;

//TODO: convert to builder pattern
public class Note {
	private String key;
	private int priority;
	private String className;
	private ElementKind elementKind;

	public Note(String key, int priority, String className) {
		super();
		this.key = key;
		this.priority = priority;
		this.className = className;
	}

	public Note(String key, int priority, String className, ElementKind elementKind) {
		super();
		this.key = key;
		this.priority = priority;
		this.className = className;
		this.elementKind = elementKind;
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

    public ElementKind getElementKind() {
        return elementKind;
    }

    public void setElementKind(ElementKind elementKind) {
        this.elementKind = elementKind;
    }

}
