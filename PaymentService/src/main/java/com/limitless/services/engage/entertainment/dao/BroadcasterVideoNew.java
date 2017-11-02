package com.limitless.services.engage.entertainment.dao;

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
@Table(name="broadcaster_videos")
public class BroadcasterVideoNew {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	@Column(name="BROADCASTER_CHANNEL_ID")
	private Integer broadcasterChannelId;
	@Column(name="LANGUAGE_ID")
	private Integer languageId;
	@Column(name="VIDEO_NAME")
	private String videoName;
	@Column(name="IS_ACTIVE", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isActive;
	@Column(name="RANK")
	private Integer rank;
	@Column(name="VIDEO_THUMBNAIL")
	private String videoThumbnail;
	@Column(name="VIDEO_DESCRIPTION")
	private String videoDescription;
	@Column(name="URL")
	private String url;
	@Column(name="DURATION")
	private Integer duration;
	@Column(name="IS_PRIMARY", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isPrimary;
	@Column(name="IS_LIVE", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isLive;
	@Column(name="IS_YOUTUBE", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isYoutube;
	@Column(name="LIVE_ADS", nullable=false, columnDefinition="TINYINT(1)")
	private boolean liveAds;
	@Column(name="P160", nullable=false, columnDefinition="TINYINT(1)")
	private boolean p160;
	@Column(name="P360", nullable=false, columnDefinition="TINYINT(1)")
	private boolean p360;
	@Column(name="P720", nullable=false, columnDefinition="TINYINT(1)")
	private boolean p720;
	@Column(name="P1080", nullable=false, columnDefinition="TINYINT(1)")
	private boolean p1080;
	@Column(name="P_UHD")
	private boolean pUhd;
	@Column(name="VIDEO_TYPE")
	private String videoType;
	@Column(name="YT_STREAMKEY")
	private String ytStreamkey;
	@Column(name="FB_STREAMKEY")
	private String fbStreamkey;
	@Column(name="HA_STREAMKEY")
	private String haStreamkey;
	@Column(name="PS_STREAMKEY")
	private String psStreamkey;
	@Column(name="VIDEO_CREATED_TIME", insertable = false, updatable = false)
	private Date videoCreatedTime;
	@Column(name="VIDEO_UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date videoUpdatedTIme;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBroadcasterChannelId() {
		return broadcasterChannelId;
	}
	public void setBroadcasterChannelId(Integer broadcasterChannelId) {
		this.broadcasterChannelId = broadcasterChannelId;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getVideoThumbnail() {
		return videoThumbnail;
	}
	public void setVideoThumbnail(String videoThumbnail) {
		this.videoThumbnail = videoThumbnail;
	}
	public String getVideoDescription() {
		return videoDescription;
	}
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	public boolean isYoutube() {
		return isYoutube;
	}
	public void setYoutube(boolean isYoutube) {
		this.isYoutube = isYoutube;
	}
	public boolean isLiveAds() {
		return liveAds;
	}
	public void setLiveAds(boolean liveAds) {
		this.liveAds = liveAds;
	}
	public boolean isP160() {
		return p160;
	}
	public void setP160(boolean p160) {
		this.p160 = p160;
	}
	public boolean isP360() {
		return p360;
	}
	public void setP360(boolean p360) {
		this.p360 = p360;
	}
	public boolean isP720() {
		return p720;
	}
	public void setP720(boolean p720) {
		this.p720 = p720;
	}
	public boolean isP1080() {
		return p1080;
	}
	public void setP1080(boolean p1080) {
		this.p1080 = p1080;
	}
	public boolean ispUhd() {
		return pUhd;
	}
	public void setpUhd(boolean pUhd) {
		this.pUhd = pUhd;
	}
	public String getVideoType() {
		return videoType;
	}
	public void setVideoType(String videoType) {
		this.videoType = videoType;
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
	public String getHaStreamkey() {
		return haStreamkey;
	}
	public void setHaStreamkey(String haStreamkey) {
		this.haStreamkey = haStreamkey;
	}
	public String getPsStreamkey() {
		return psStreamkey;
	}
	public void setPsStreamkey(String psStreamkey) {
		this.psStreamkey = psStreamkey;
	}
	public Date getVideoCreatedTime() {
		return videoCreatedTime;
	}
	public void setVideoCreatedTime(Date videoCreatedTime) {
		this.videoCreatedTime = videoCreatedTime;
	}
	public Date getVideoUpdatedTIme() {
		return videoUpdatedTIme;
	}
	public void setVideoUpdatedTIme(Date videoUpdatedTIme) {
		this.videoUpdatedTIme = videoUpdatedTIme;
	}
}
