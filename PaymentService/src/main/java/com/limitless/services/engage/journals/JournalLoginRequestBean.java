package com.limitless.services.engage.journals;

public class JournalLoginRequestBean {
	private String journalEmail;
	private String journalPassword;
	private String journalDeviceMacId;
	public String getJournalEmail() {
		return journalEmail;
	}
	public void setJournalEmail(String journalEmail) {
		this.journalEmail = journalEmail;
	}
	public String getJournalPassword() {
		return journalPassword;
	}
	public void setJournalPassword(String journalPassword) {
		this.journalPassword = journalPassword;
	}
	public String getJournalDeviceMacId() {
		return journalDeviceMacId;
	}
	public void setJournalDeviceMacId(String journalDeviceMacId) {
		this.journalDeviceMacId = journalDeviceMacId;
	}
	
}
