package com.limitless.services.engage.journals.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="journal")
public class Journal {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer journalId;
	@Column(name="CHANNEL_ID")
	private Integer journalChannelId;
	@Column(name="EMAIL")
	private String journalEmail;
	@Column(name="PASSWORD")
	private String journalPassword;
	@Column(name="EMP_ID")
	private String journalEmpId;
	@Column(name="FIRST_NAME")
	private String journalFirstName;
	@Column(name="LAST_NAME")
	private String journalLastName;
	@Column(name="MOBILE")
	private String journalMobile;
	@Column(name="IS_ACTIVE", nullable=false, columnDefinition="TINYINT(1)")
	private boolean journalIsActive;
	@Column(name="IS_DELETED", nullable=false, columnDefinition="TINYINT(1)")
	private boolean journalIsDeleted;
	@Column(name="CREATED_BY")
	private String journalCreatedBy;
	@Column(name="UPDATED_BY")
	private String journalUpdatedBy;
	@Column(name="CREATED_TIME", insertable = false, updatable = false)
	private Date journalCreatedTime;
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date journalUpdatedTime;
	
	public Integer getJournalId() {
		return journalId;
	}
	public void setJournalId(Integer journalId) {
		this.journalId = journalId;
	}
	public Integer getJournalChannelId() {
		return journalChannelId;
	}
	public void setJournalChannelId(Integer journalChannelId) {
		this.journalChannelId = journalChannelId;
	}
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
	public Date getJournalCreatedTime() {
		return journalCreatedTime;
	}
	public void setJournalCreatedTime(Date journalCreatedTime) {
		this.journalCreatedTime = journalCreatedTime;
	}
	public Date getJournalUpdatedTime() {
		return journalUpdatedTime;
	}
	public void setJournalUpdatedTime(Date journalUpdatedTime) {
		this.journalUpdatedTime = journalUpdatedTime;
	}
	
}
