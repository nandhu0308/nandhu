package com.limitless.services.engage;

public class CustomerNotifyResponseBean {
	private int notifyId;
	private String message;
	private int customerId;
	public int getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(int notifyId) {
		this.notifyId = notifyId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}	
}
