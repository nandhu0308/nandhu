package com.limitless.services.engage.ads;

public class AssignVideoAds {
	private int id;
	private int videoAdEventId;
	private int videoAdId;
	private int adLength;
	private String adTarget;
	private String videoFtpPath;
	private String videoName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVideoAdEventId() {
		return videoAdEventId;
	}
	public void setVideoAdEventId(int videoAdEventId) {
		this.videoAdEventId = videoAdEventId;
	}
	public int getVideoAdId() {
		return videoAdId;
	}
	public void setVideoAdId(int videoAdId) {
		this.videoAdId = videoAdId;
	}
	public int getAdLength() {
		return adLength;
	}
	public void setAdLength(int adLength) {
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
}
