package com.limitless.services.engage.journals.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "journal_setting")
public class JournalSetting {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer journalSettingId;
	@Column(name = "JOURNAL_ID")
	private Integer journalId;
	@Column(name = "LANGUAGE_ID")
	private Integer languageId;
	@Column(name = "APPLN_NAME")
	private String applicationName;
	@Column(name = "HOST_URL")
	private String hostURL;
	@Column(name = "HOST_PORT")
	private String hostPort;
	@Column(name = "STREAM_NAME")
	private String streamName;
	@Column(name = "SUNAME")
	private String suNAme;
	@Column(name = "SPWD")
	private String sPWD;
	@Column(name = "REP_MAC_ADDR")
	private String repMacAddr;
	@Column(name = "OUTPUT_URL_HLS")
	private String outputUrlHls;
	@Column(name = "OUTPUT_URL_RTSP")
	private String outputUrlRtsp;
	@Column(name = "IS_RECORD", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean isRecord;
	@Column(name = "record_user_name")
	private String record_user_name;
	@Column(name = "record_password")
	private String record_password;
	@Column(name = "IS_UPLOAD", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean isUpload;
	@Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean isActive;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "CREATED_TIME", insertable = false, updatable = false)
	private Date createdTime;
	@Column(name = "UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date updatedTime;

	@OneToOne(mappedBy = "deviceSetting")
	private JournalDevices journalDevices;

	private int frames_per_second;
	private int keyframe_interval;
	private int video_bitrate;
	private int audio_bitrate;
	private int video_frame_width;
	private int video_frame_height;
	@Column(name = "ABR", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean abr;
	@Column(name = "YT_STREAMKEY")
	private String ytStreamkey;
	@Column(name = "FB_STREAMKEY")
	private String fbStreamkey;
	@Column(name = "PS_STREAMKEY")
	private String psStreamkey;
	@Column(name = "ENABLE_SOCIAL_MEDIA", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean enableSocialMedia;	
	private String fb_page_names;
	

	
	public String getFb_page_names() {
		return fb_page_names;
	}

	public void setFb_page_names(String fb_page_names) {
		this.fb_page_names = fb_page_names;
	}

	public boolean isEnableSocialMedia() {
		return enableSocialMedia;
	}

	public void setEnableSocialMedia(boolean enableSocialMedia) {
		this.enableSocialMedia = enableSocialMedia;
	}

	
	public String getYtStreamkey() {
		return ytStreamkey;
	}

	public void setYtStreamkey(String ytStreamkey) {
		this.ytStreamkey = ytStreamkey;
	}

	public String getFbStreamkey() {
		return fbStreamkey;
	}

	public void setFbStreamkey(String fbStreamkey) {
		this.fbStreamkey = fbStreamkey;
	}

	public String getPsStreamkey() {
		return psStreamkey;
	}

	public void setPsStreamkey(String psStreamkey) {
		this.psStreamkey = psStreamkey;
	}

	public int getFrames_per_second() {
		return frames_per_second;
	}

	public void setFrames_per_second(int frames_per_second) {
		this.frames_per_second = frames_per_second;
	}

	public int getKeyframe_interval() {
		return keyframe_interval;
	}

	public void setKeyframe_interval(int keyframe_interval) {
		this.keyframe_interval = keyframe_interval;
	}

	public int getVideo_bitrate() {
		return video_bitrate;
	}

	public void setVideo_bitrate(int video_bitrate) {
		this.video_bitrate = video_bitrate;
	}

	public int getAudio_bitrate() {
		return audio_bitrate;
	}

	public void setAudio_bitrate(int audio_bitrate) {
		this.audio_bitrate = audio_bitrate;
	}

	public int getVideo_frame_width() {
		return video_frame_width;
	}

	public void setVideo_frame_width(int video_frame_width) {
		this.video_frame_width = video_frame_width;
	}

	public int getVideo_frame_height() {
		return video_frame_height;
	}

	public void setVideo_frame_height(int video_frame_height) {
		this.video_frame_height = video_frame_height;
	}

	public boolean isAbr() {
		return abr;
	}

	public void setAbr(boolean abr) {
		this.abr = abr;
	}

	public JournalDevices getJournalDevices() {
		return journalDevices;
	}

	public void setJournalDevices(JournalDevices journalDevices) {
		this.journalDevices = journalDevices;
	}

	public String getRecord_user_name() {
		return record_user_name;
	}

	public void setRecord_user_name(String record_user_name) {
		this.record_user_name = record_user_name;
	}

	public String getRecord_password() {
		return record_password;
	}

	public void setRecord_password(String record_password) {
		this.record_password = record_password;
	}

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
		return applicationName.trim();
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getHostURL() {
		return hostURL.trim();
	}

	public void setHostURL(String hostURL) {
		this.hostURL = hostURL;
	}

	public String getHostPort() {
		return hostPort.trim();
	}

	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	public String getStreamName() {
		return streamName.trim();
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
