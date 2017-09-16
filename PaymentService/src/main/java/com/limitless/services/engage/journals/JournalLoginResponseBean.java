package com.limitless.services.engage.journals;

import java.util.List;

public class JournalLoginResponseBean {
	private int journalId;
	private int journalChannelId;
	private String journalEmail;
	private String journalEmpId;
	private String journalFirstName;
	private String journalLastName;
	private String journalMobile;
	private boolean journalIsActive;
	private boolean journalIsDeleted;
	private String journalCreatedBy;
	private String journalUpdatedBy;
	private List<JournalDeviceBean> journalDeviceList;
	private JournalSettingBean journalSetting;
	public int getJournalId() {
		return journalId;
	}
	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}
	public int getJournalChannelId() {
		return journalChannelId;
	}
	public void setJournalChannelId(int journalChannelId) {
		this.journalChannelId = journalChannelId;
	}
	public String getJournalEmail() {
		return journalEmail;
	}
	public void setJournalEmail(String journalEmail) {
		this.journalEmail = journalEmail;
	}
	public String getJournalEmpId() {
		return journalEmpId;
	}
	public void setJournalEmpId(String journalEmpId) {
		this.journalEmpId = journalEmpId;
	}
	public String getJournalFirstName() {
		return journalFirstName;
	}
	public void setJournalFirstName(String journalFirstName) {
		this.journalFirstName = journalFirstName;
	}
	public String getJournalLastName() {
		return journalLastName;
	}
	public void setJournalLastName(String journalLastName) {
		this.journalLastName = journalLastName;
	}
	public String getJournalMobile() {
		return journalMobile;
	}
	public void setJournalMobile(String journalMobile) {
		this.journalMobile = journalMobile;
	}
	public boolean isJournalIsActive() {
		return journalIsActive;
	}
	public void setJournalIsActive(boolean journalIsActive) {
		this.journalIsActive = journalIsActive;
	}
	public boolean isJournalIsDeleted() {
		return journalIsDeleted;
	}
	public void setJournalIsDeleted(boolean journalIsDeleted) {
		this.journalIsDeleted = journalIsDeleted;
	}
	public String getJournalCreatedBy() {
		return journalCreatedBy;
	}
	public void setJournalCreatedBy(String journalCreatedBy) {
		this.journalCreatedBy = journalCreatedBy;
	}
	public String getJournalUpdatedBy() {
		return journalUpdatedBy;
	}
	public void setJournalUpdatedBy(String journalUpdatedBy) {
		this.journalUpdatedBy = journalUpdatedBy;
	}
	public List<JournalDeviceBean> getJournalDeviceList() {
		return journalDeviceList;
	}
	public void setJournalDeviceList(List<JournalDeviceBean> journalDeviceList) {
		this.journalDeviceList = journalDeviceList;
	}
	public JournalSettingBean getJournalSetting() {
		return journalSetting;
	}
	public void setJournalSetting(JournalSettingBean journalSetting) {
		this.journalSetting = journalSetting;
	}
	
}
