package com.limitless.services.engage.journals;

public class JournalDeviceBean {
	private int journalDeviceId;
	private int journalId;
	private String journalDeviceMacId;
	private boolean journalDeviceIsActive;
	private String createdBy;
	private String updatedBy;
	public int getJournalDeviceId() {
		return journalDeviceId;
	}
	public void setJournalDeviceId(int journalDeviceId) {
		this.journalDeviceId = journalDeviceId;
	}
	public int getJournalId() {
		return journalId;
	}
	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}
	public String getJournalDeviceMacId() {
		return journalDeviceMacId;
	}
	public void setJournalDeviceMacId(String journalDeviceMacId) {
		this.journalDeviceMacId = journalDeviceMacId;
	}
	public boolean isJournalDeviceIsActive() {
		return journalDeviceIsActive;
	}
	public void setJournalDeviceIsActive(boolean journalDeviceIsActive) {
		this.journalDeviceIsActive = journalDeviceIsActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
