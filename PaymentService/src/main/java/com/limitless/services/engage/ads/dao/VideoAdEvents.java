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
@Table(name="video_ad_event")
public class VideoAdEvents {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	@Column(name="CHANNEL_ID")
	private Integer channelId;
	@Column(name="EVENT_NAME")
	private String eventName;
	@Column(name="DATE")
	private String date;
	@Column(name="NO_OF_ADS")
	private Integer noOfAds;
	@Column(name="START_TIME")
	private String startTime;
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
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getNoOfAds() {
		return noOfAds;
	}
	public void setNoOfAds(Integer noOfAds) {
		this.noOfAds = noOfAds;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
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
