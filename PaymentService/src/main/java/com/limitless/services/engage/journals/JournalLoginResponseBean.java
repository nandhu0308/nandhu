package com.limitless.services.engage.journals;

import java.util.List;

public class JournalLoginResponseBean {

	private JournalBean journal;
	private JournalSettingBean journalSetting;
	private String authKey;

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public JournalBean getJournal() {
		return journal;
	}

	public void setJournal(JournalBean journal) {
		this.journal = journal;
	}

	public JournalSettingBean getJournalSetting() {
		return journalSetting;
	}

	public void setJournalSetting(JournalSettingBean journalSetting) {
		this.journalSetting = journalSetting;
	}

}
