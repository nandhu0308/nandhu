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
@Table(name="ad_events")
public class AdEvents {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	@Column(name="CHANNEL_ID")
	private Integer channelId;
	@Column(name="EVENT_NAME")
	private String eventName;
	@Column(name="EVENT_TYPE")
	private String eventType;
	@Column(name="DURATION")
	private Integer duration;
	@Column(name="DATE")
	private String date;
	@Column(name="START_TIME")
	private String startTime;
	@Column(name="END_TIME")
	private String endTime;
	@Column(name="AD_WINDOW_TIME_PA")
	private Integer adWindowTime;
	@Column(name="IS_ACTIVE", nullable=false, columnDefinition="TINYINT(1)")
	private boolean isActive;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="CREATED_ON", insertable = false, updatable = false)
	private Date createdOn;
	@Column(name="UPDATED_BY")
	private String updatedBy;
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
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getAdWindowTime() {
		return adWindowTime;
	}
	public void setAdWindowTime(Integer adWindowTime) {
		this.adWindowTime = adWindowTime;
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
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
