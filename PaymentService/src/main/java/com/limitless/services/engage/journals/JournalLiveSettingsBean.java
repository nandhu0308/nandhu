package com.limitless.services.engage.journals;

public class JournalLiveSettingsBean {
	private int journalId;
	private int videoId;
	private String currentFBStreamKey;
	private String newFBStreamKey;
	private String currentYTStreamKey;
	private String newYTStreamKey;
	private String currentHAStreamKey;
	private String newHAStreamKey;
	private String currentPSStreamKey;
	private String newPSStreamKey;
	private String fbPageId;
	private boolean youtubeEnabled = false, facebookEnabled = false, periscopeEnabled = false;

	public boolean isYoutubeKeyUpdated() {
		return currentYTStreamKey != null && newYTStreamKey != null
				&& !currentYTStreamKey.equalsIgnoreCase(newYTStreamKey) && !youtubeEnabled;
	}

	public boolean isYoutubeEnabled() {
		return youtubeEnabled;
	}

	public void setYoutubeEnabled(boolean youtubeEnabled) {
		this.youtubeEnabled = youtubeEnabled;
	}

	public boolean isFacebookEnabled() {
		return facebookEnabled;
	}

	public void setFacebookEnabled(boolean facebookEnabled) {
		this.facebookEnabled = facebookEnabled;
	}

	public boolean isPeriscopeEnabled() {
		return periscopeEnabled;
	}

	public void setPeriscopeEnabled(boolean periscopeEnabled) {
		this.periscopeEnabled = periscopeEnabled;
	}

	public String getFbPageId() {
		return fbPageId;
	}

	public void setFbPageId(String fbPageId) {
		this.fbPageId = fbPageId;
	}

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getCurrentFBStreamKey() {
		return currentFBStreamKey;
	}

	public void setCurrentFBStreamKey(String currentFBStreamKey) {
		this.currentFBStreamKey = currentFBStreamKey;
	}

	public String getNewFBStreamKey() {
		return newFBStreamKey;
	}

	public void setNewFBStreamKey(String newFBStreamKey) {
		this.newFBStreamKey = newFBStreamKey;
	}

	public String getCurrentYTStreamKey() {
		return currentYTStreamKey;
	}

	public void setCurrentYTStreamKey(String currentYTStreamKey) {
		this.currentYTStreamKey = currentYTStreamKey;
	}

	public String getNewYTStreamKey() {
		return newYTStreamKey;
	}

	public void setNewYTStreamKey(String newYTStreamKey) {
		this.newYTStreamKey = newYTStreamKey;
	}

	public String getCurrentHAStreamKey() {
		return currentHAStreamKey;
	}

	public void setCurrentHAStreamKey(String currentHAStreamKey) {
		this.currentHAStreamKey = currentHAStreamKey;
	}

	public String getNewHAStreamKey() {
		return newHAStreamKey;
	}

	public void setNewHAStreamKey(String newHAStreamKey) {
		this.newHAStreamKey = newHAStreamKey;
	}

	public String getCurrentPSStreamKey() {
		return currentPSStreamKey;
	}

	public void setCurrentPSStreamKey(String currentPSStreamKey) {
		this.currentPSStreamKey = currentPSStreamKey;
	}

	public String getNewPSStreamKey() {
		return newPSStreamKey;
	}

	public void setNewPSStreamKey(String newPSStreamKey) {
		this.newPSStreamKey = newPSStreamKey;
	}
}
