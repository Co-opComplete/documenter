package com.scuilion.documenter;

import javax.lang.model.element.*;

public class Note {
	private String key;
	private int priority;
	private String className;
	private String packageName;
	private ElementKind elementKind;

	public static class NoteBuilder{
        private String key;
        private int priority;
        private String className;
        private String packageName;
        private ElementKind elementKind;
        public NoteBuilder(){
        }
        public NoteBuilder key(String key) {
            this.key = key;
            return this;
        }
        public NoteBuilder priority(int priority) {
            this.priority = priority;
            return this;
        }
        public NoteBuilder className(String className) {
            this.className = className;
            return this;
        }
        public NoteBuilder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }
        public NoteBuilder elementKind(ElementKind elementKind) {
            this.elementKind = elementKind;
            return this;
        }
        public Note build(){
            return new Note(this.key, this.priority, this.className, this.elementKind,
                    this.packageName);
        }
	}

	private Note(String key, int priority, String className, ElementKind elementKind, String packageName) {
		this.key = key;
		this.priority = priority;
		this.className = className;
		this.elementKind = elementKind;
		this.packageName = packageName;
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
