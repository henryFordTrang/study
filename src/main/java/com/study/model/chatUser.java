package com.study.model;

public class chatUser {
	private String id;
	private String message;
	private String isPic;
	public chatUser() {
		super();
	}
	public chatUser(String id, String message, String isPic) {
		super();
		this.id = id;
		this.message = message;
		this.isPic = isPic;
	}
	@Override
	public String toString() {
		return "chatUser [id=" + id + ", message=" + message + ", isPic=" + isPic + "]";
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
	public String getIsPic() {
		return isPic;
	}
	public void setIsPic(String isPic) {
		this.isPic = isPic;
	}
	
	
	
}
