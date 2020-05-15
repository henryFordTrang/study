package com.study.model;

public class chatUser {
	private String id;
	private String message;
	public chatUser(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	public chatUser() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "chatUser [id=" + id + ", message=" + message + "]";
	}
	
}
