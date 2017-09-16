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
@Table(name="journal_setting")
public class JournalSetting {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer journalSettingId;
	@Column(name="JOURNAL_ID")
	private Integer journalId;
	@Column(name="LANGUAGE_ID")
	private Integer languageId;
	@Column(name="APPLN_NAME")
	private String applicationName;
	@Column(name="HOST_URL")
	private String hostURL;
	@Column(name="HOST_PORT")
	private String hostPort;
	@Column(name="STREAM_NAME")
	private String streamName;
	@Column(name="SUNAME")
	private String suNAme;
	@Column(name="SPWD")
	private String sPWD;
	@Column(name="REP_MAC_ADDR")
	private String repMacAddr;
	@Column(name="OUTPUT_URL_HLS")
	private String outputUrlHls;
	@Column(name="OUTPUT_URL_RTSP")
	private String outputUrlRtsp;
	@Column(name="IS_RECORD", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isRecord;
	@Column(name="IS_UPLOAD", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isUpload;
	@Column(name="IS_ACTIVE", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isActive;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="CREATED_TIME", insertable=false, updatable=false)
	private Date createdTime;
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date updatedTime;
	public Integer getJournalSettingId() {
		return journalSettingId;
	}
	public void setJournalSettingId(Integer journalSettingId) {
		this.journalSettingId = journalSettingId;
	}
	public Integer getJournalId() {
		return journalId;
	}
	public void setJournalId(Integer journalId) {
		this.journalId = journalId;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getHostURL() {
		return hostURL;
	}
	public void setHostURL(String hostURL) {
		this.hostURL = hostURL;
	}
	public String getHostPort() {
		return hostPort;
	}
	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getSuNAme() {
		return suNAme;
	}
	public void setSuNAme(String suNAme) {
		this.suNAme = suNAme;
	}
	public String getsPWD() {
		return sPWD;
	}
	public void setsPWD(String sPWD) {
		this.sPWD = sPWD;
	}
	public String getRepMacAddr() {
		return repMacAddr;
	}
	public void setRepMacAddr(String repMacAddr) {
		this.repMacAddr = repMacAddr;
	}
	public String getOutputUrlHls() {
		return outputUrlHls;
	}
	public void setOutputUrlHls(String outputUrlHls) {
		this.outputUrlHls = outputUrlHls;
	}
	public String getOutputUrlRtsp() {
		return outputUrlRtsp;
	}
	public void setOutputUrlRtsp(String outputUrlRtsp) {
		this.outputUrlRtsp = outputUrlRtsp;
	}
	public boolean isRecord() {
		return isRecord;
	}
	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}
	public boolean isUpload() {
		return isUpload;
	}
	public void setUpload(boolean isUpload) {
		this.isUpload = isUpload;
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
