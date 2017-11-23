package com.limitless.services.engage.ads.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="assign_logo_ads")
public class AssignLogoAds {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	@Column(name="LOGO_AD_ID")
	private Integer logoAdId;
	@Column(name="AD_EVENT_ID")
	private Integer adEventId;
	@Column(name="TIME_SLOT_START")
	private String timeSlotStart;
	@Column(name="TIME_SLOT_END")
	private String timeSlotEnd;
	@Column(name="AD_PLACEMENT")
	private String adPlacement;
	@Column(name="AD_TARGET")
	private String adTarget;
	@Column(name="GEO_X_COORDINATE")
	private String geoXCoordinate;
	@Column(name="GEO_Y_COORDINATE")
	private String geoYCoordinate;
	@Column(name="LOGO_FTP_PATH")
	private String logoFtpPath;
	@Column(name="IMG_NAME")
	private String imgName;
	@Column(name="STREAM_SOURCE")
	private String streamSource;
	@Column(name="LOWER_TEXT")
	private String lowerText;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLogoAdId() {
		return logoAdId;
	}
	public void setLogoAdId(Integer logoAdId) {
		this.logoAdId = logoAdId;
	}
	public Integer getAdEventId() {
		return adEventId;
	}
	public void setAdEventId(Integer adEventId) {
		this.adEventId = adEventId;
	}
	public String getTimeSlotStart() {
		return timeSlotStart;
	}
	public void setTimeSlotStart(String timeSlotStart) {
		this.timeSlotStart = timeSlotStart;
	}
	public String getTimeSlotEnd() {
		return timeSlotEnd;
	}
	public void setTimeSlotEnd(String timeSlotEnd) {
		this.timeSlotEnd = timeSlotEnd;
	}
	public String getAdPlacement() {
		return adPlacement;
	}
	public void setAdPlacement(String adPlacement) {
		this.adPlacement = adPlacement;
	}
	public String getAdTarget() {
		return adTarget;
	}
	public void setAdTarget(String adTarget) {
		this.adTarget = adTarget;
	}
	public String getGeoXCoordinate() {
		return geoXCoordinate;
	}
	public void setGeoXCoordinate(String geoXCoordinate) {
		this.geoXCoordinate = geoXCoordinate;
	}
	public String getGeoYCoordinate() {
		return geoYCoordinate;
	}
	public void setGeoYCoordinate(String geoYCoordinate) {
		this.geoYCoordinate = geoYCoordinate;
	}
	public String getLogoFtpPath() {
		return logoFtpPath;
	}
	public void setLogoFtpPath(String logoFtpPath) {
		this.logoFtpPath = logoFtpPath;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getStreamSource() {
		return streamSource;
	}
	public void setStreamSource(String streamSource) {
		this.streamSource = streamSource;
	}
	public String getLowerText() {
		return lowerText;
	}
	public void setLowerText(String lowerText) {
		this.lowerText = lowerText;
	}
}
