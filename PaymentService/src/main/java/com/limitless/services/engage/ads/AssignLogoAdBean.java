package com.limitless.services.engage.ads;

public class AssignLogoAdBean {
	private int id;
	private int logoAdId;
	private int adEventId;
	private String timeSlotStart;
	private String timeSlotEnd;
	private String adPlacement;
	private String adTarget;
	private String geoXCoordinate;
	private String geoYCoordinate;
	private String logoFtpPath;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLogoAdId() {
		return logoAdId;
	}
	public void setLogoAdId(int logoAdId) {
		this.logoAdId = logoAdId;
	}
	public int getAdEventId() {
		return adEventId;
	}
	public void setAdEventId(int adEventId) {
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
}
