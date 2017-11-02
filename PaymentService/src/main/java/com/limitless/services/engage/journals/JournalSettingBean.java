package com.limitless.services.engage.journals;

public class JournalSettingBean {

	private int id, journalId, languageId, videoFrameWidth, videoFrameHeight, keyFrameInterval, videoBitrate,
			audioBitrate,framesPerSecond;
	private String applnName, hostUrl, hostPort, streamName, suName, spwd, outputUrlHls, outputUrlRtsp, recordUserName,
			recordPassword;
	private boolean isRecord, isUpload, abr;
	

	public int getFramesPerSecond() {
		return framesPerSecond;
	}

	public void setFramesPerSecond(int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}

	public int getVideoFrameWidth() {
		return videoFrameWidth;
	}

	public void setVideoFrameWidth(int videoFrameWidth) {
		this.videoFrameWidth = videoFrameWidth;
	}

	public int getVideoFrameHeight() {
		return videoFrameHeight;
	}

	public void setVideoFrameHeight(int videoFrameHeight) {
		this.videoFrameHeight = videoFrameHeight;
	}

	public int getKeyFrameInterval() {
		return keyFrameInterval;
	}

	public void setKeyFrameInterval(int keyFrameInterval) {
		this.keyFrameInterval = keyFrameInterval;
	}

	public int getVideoBitrate() {
		return videoBitrate;
	}

	public void setVideoBitrate(int videoBitrate) {
		this.videoBitrate = videoBitrate;
	}

	public int getAudioBitrate() {
		return audioBitrate;
	}

	public void setAudioBitrate(int audioBitrate) {
		this.audioBitrate = audioBitrate;
	}

	public boolean isAbr() {
		return abr;
	}

	public void setAbr(boolean abr) {
		this.abr = abr;
	}

	public String getRecordUserName() {
		return recordUserName;
	}

	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
	}

	public String getRecordPassword() {
		return recordPassword;
	}

	public void setRecordPassword(String recordPassword) {
		this.recordPassword = recordPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getApplnName() {
		return applnName;
	}

	public void setApplnName(String applnName) {
		this.applnName = applnName;
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
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
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

}
