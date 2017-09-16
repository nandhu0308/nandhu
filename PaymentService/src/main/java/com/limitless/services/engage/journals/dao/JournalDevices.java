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
@Table(name="journal_devices")
public class JournalDevices {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer journalDeviceId;
	@Column(name="JOURNAL_ID")
	private Integer journalId;
	@Column(name="MAC_ID")
	private String journalDeviceMacId;
	@Column(name="IS_ACTIVE", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isActive;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="CREATED_TIME", insertable = false, updatable = false)
	private Date createdTime;
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date updatedTime;
	
	public Integer getJournalDeviceId() {
		return journalDeviceId;
	}
	public void setJournalDeviceId(Integer journalDeviceId) {
		this.journalDeviceId = journalDeviceId;
	}
	public Integer getJournalId() {
		return journalId;
	}
	public void setJournalId(Integer journalId) {
		this.journalId = journalId;
	}
	public String getJournalDeviceMacId() {
		return journalDeviceMacId;
	}
	public void setJournalDeviceMacId(String journalDeviceMacId) {
		this.journalDeviceMacId = journalDeviceMacId;
	}

	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
