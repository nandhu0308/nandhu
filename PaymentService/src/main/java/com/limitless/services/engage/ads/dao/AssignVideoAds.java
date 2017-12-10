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
@Table(name="assign_video_ad")
public class AssignVideoAds {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	@Column(name="VIDEO_AD_EVENT_ID")
	private Integer videoAdEventId;
	@Column(name="VIDEO_AD_ID")
	private Integer videoAdId;
	@Column(name="AD_LENGTH")
	private Integer adLength;
	@Column(name="AD_TARGET")
	private String adTarget;
	@Column(name="VIDEO_FTP_PATH")
	private String videoFtpPath;
	@Column(name="VIDEO_NAME")
	private String videoName;
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
	public Integer getVideoAdEventId() {
		return videoAdEventId;
	}
	public void setVideoAdEventId(Integer videoAdEventId) {
		this.videoAdEventId = videoAdEventId;
	}
	public Integer getVideoAdId() {
		return videoAdId;
	}
	public void setVideoAdId(Integer videoAdId) {
		this.videoAdId = videoAdId;
	}
	public Integer getAdLength() {
		return adLength;
	}
	public void setAdLength(Integer adLength) {
		this.adLength = adLength;
	}
	public String getAdTarget() {
		return adTarget;
	}
	public void setAdTarget(String adTarget) {
		this.adTarget = adTarget;
	}
	public String getVideoFtpPath() {
		return videoFtpPath;
	}
	public void setVideoFtpPath(String videoFtpPath) {
		this.videoFtpPath = videoFtpPath;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
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
