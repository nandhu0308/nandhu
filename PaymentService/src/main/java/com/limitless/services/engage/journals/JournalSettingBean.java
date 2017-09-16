package com.limitless.services.engage.journals;

public class JournalSettingBean {
	private int journalSettingId;
	private int journalId;
	private int languageId;
	private String applicationName;
	private String hostUrl;
	private String hostPort;
	private String StreamName;
	private String suName;
	private String sPWD;
	private String repMacAddr;
	private String outputUrlHls;
	private String outputUrlRtsp;
	private boolean isRecord;
	private boolean isUpload;
	private boolean isActive;
	private String createdBy;
	private String updatedBy;
	
	public int getJournalSettingId() {
		return journalSettingId;
	}
	public void setJournalSettingId(int journalSettingId) {
		this.journalSettingId = journalSettingId;
	}
	public int getJournalId() {
		return journalId;
	}
	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	public String getHostPort() {
		return hostPort;
	}
	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}
	public String getStreamName() {
		return StreamName;
	}
	public void setStreamName(String streamName) {
		StreamName = streamName;
	}
	public String getSuName() {
		return suName;
	}
	public void setSuName(String suName) {
		this.suName = suName;
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
	
}
