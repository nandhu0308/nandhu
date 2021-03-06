package com.limitless.services.payment.PaymentService;

public class NotificationRequestBean {
	private String to;
	private String priority = "high";
	private NotificationBean notification;
	private DataBean data;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public NotificationBean getNotification() {
		return notification;
	}

	public void setNotification(NotificationBean notification) {
		this.notification = notification;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

}
