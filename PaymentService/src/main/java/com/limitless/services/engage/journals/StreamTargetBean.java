package com.limitless.services.engage.journals;

public class StreamTargetBean {
	private String serverName;
	private String sourceStreamName;
	private String entryName;
	private String profile;
	private String host;
	private String application;
	private String userName;
	private String password;
	private String streamName;
	private int port;
	private boolean autoStartTransacoder;
	private boolean enabled;
	private String appInstance;
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getSourceStreamName() {
		return sourceStreamName;
	}
	public void setSourceStreamName(String sourceStreamName) {
		this.sourceStreamName = sourceStreamName;
	}
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isAutoStartTransacoder() {
		return autoStartTransacoder;
	}
	public void setAutoStartTransacoder(boolean autoStartTransacoder) {
		this.autoStartTransacoder = autoStartTransacoder;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAppInstance() {
		return appInstance;
	}
	public void setAppInstance(String appInstance) {
		this.appInstance = appInstance;
	}
}
