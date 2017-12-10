package com.limitless.services.engage.ads.dao;

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
@Table(name="ha_video_ads")
public class HAVideoAds {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	@Column(name="BROADCASTER_ID")
	private Integer broadcasterId;
	@Column(name="CHANNEL_ID")
	private Integer channelId;
	@Column(name="AD_TITLE")
	private String adTitle;
	@Column(name="AD_LENGTH")
	private Integer adLength;
	@Column(name="FTP_PATH")
	private String ftpPath;
	@Column(name="IS_ACTIVE", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isActive;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="CREATED_ON", insertable = false, updatable = false)
	private Date createdOn;
	@Column(name="UPDATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date updatedOn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBroadcasterId() {
		return broadcasterId;
	}
	public void setBroadcasterId(Integer broadcasterId) {
		this.broadcasterId = broadcasterId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public Integer getAdLength() {
		return adLength;
	}
	public void setAdLength(Integer adLength) {
		this.adLength = adLength;
	}
	public String getFtpPath() {
		return ftpPath;
	}
	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
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
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
