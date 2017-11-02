package com.limitless.services.engage.journals.dao;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

import com.limitless.services.engage.dao.SessionKeys;
import com.limitless.services.engage.entertainment.dao.BroadcasterVideo;
import com.limitless.services.engage.entertainment.dao.BroadcasterVideoNew;
import com.limitless.services.engage.journals.JournalBean;
import com.limitless.services.engage.journals.JournalLiveSettingsBean;
import com.limitless.services.engage.journals.JournalLoginRequestBean;
import com.limitless.services.engage.journals.JournalLoginResponseBean;
import com.limitless.services.engage.journals.JournalSettingBean;
import com.limitless.services.engage.journals.NewJournalResponseBean;
import com.limitless.services.payment.PaymentService.util.HibernateUtil;
import com.limitless.services.payment.PaymentService.util.RestClientUtil;
import com.limitless.services.engage.journals.dao.Journal;
import com.limitless.services.engage.journals.dao.JournalDevices;
import com.limitless.services.engage.journals.dao.JournalSetting;
import com.sun.jersey.api.client.Client;

public class JournalManager {
	private static final Log log = LogFactory.getLog(JournalManager.class);
	Client client = RestClientUtil.createClient();
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public NewJournalResponseBean addJournal(JournalBean bean) {
		NewJournalResponseBean responseBean = new NewJournalResponseBean();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Journal journal = new Journal();
			journal.setJournalChannelId(bean.getChannelId());
			journal.setJournalEmail(bean.getEmail());
			journal.setJournalPassword(bean.getPassword());
			journal.setJournalEmpId(bean.getEmpId());
			journal.setJournalFirstName(bean.getfName());
			journal.setJournalLastName(bean.getlName());
			journal.setJournalMobile(bean.getMobile());
			journal.setActive(bean.isActive());
			journal.setJournalIsDeleted(bean.isDeleted());
			journal.setJournalCreatedBy("app");
			journal.setJournalUpdatedBy("app");
			session.persist(journal);
			responseBean.setJournalId(journal.getJournalId());
			responseBean.setMessage("Success");

			transaction.commit();
		} catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("adding journal failed: " + re);
			throw re;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return responseBean;
	}

	public JournalLoginResponseBean journalLogin(JournalLoginRequestBean requestBean) {
		JournalLoginResponseBean responseBean = new JournalLoginResponseBean();
		JournalSettingBean settingBean = new JournalSettingBean();
		JournalLiveSettingsBean liveSettingsBean = new JournalLiveSettingsBean();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();

			Criteria journalCriteria = session.createCriteria(Journal.class);
			Criterion emailCriterion = Restrictions.eq("journalEmail", requestBean.getJournalEmail());
			Criterion passwdCriterion = Restrictions.eq("journalPassword", requestBean.getJournalPassword());
			LogicalExpression logExp = Restrictions.and(emailCriterion, passwdCriterion);
			journalCriteria.add(logExp);
			journalCriteria.add(Restrictions.eq("isActive", true));
			journalCriteria.add(Restrictions.eq("journalIsDeleted", false));
			List<Journal> journalList = journalCriteria.list();
			log.debug("journal list : " + journalList.size());
			if (journalList.size() == 1) {
				for (Journal journal : journalList) {
					JournalBean journalBean = new JournalBean();
					journalBean.setJournalId(journal.getJournalId());
					journalBean.setChannelId(journal.getJournalChannelId());
					journalBean.setEmail(journal.getJournalEmail());
					journalBean.setfName(journal.getJournalFirstName());
					journalBean.setlName(journal.getJournalLastName());
					journalBean.setMobile(journal.getJournalMobile());
					journalBean.setEmpId(journal.getJournalEmpId());

					Criteria deviceCriteria = session.createCriteria(JournalDevices.class);
					Criterion jidCriterion = Restrictions.eq("journalId", journal.getJournalId());
					Criterion macCriterion = Restrictions.eq("journalDeviceMacId", requestBean.getJournalDeviceMacId());
					LogicalExpression logExp2_mac = Restrictions.and(jidCriterion, macCriterion);
					deviceCriteria.add(logExp2_mac);
					deviceCriteria.add(Restrictions.eq("isActive", true));
					List<JournalDevices> journalDevicesList = deviceCriteria.list();

					Criteria settingsCriteria = session.createCriteria(JournalSetting.class);
					settingsCriteria.add(Restrictions.eq("journalId", journal.getJournalId()));
					settingsCriteria.add(Restrictions.eq("isActive", true));
					settingsCriteria.add(Restrictions.isNotNull("journalDevices"));
					List<JournalSetting> settingList = settingsCriteria.list();
					log.debug("setting list size : " + settingList.size());
					JournalSetting setting = null;
					if (settingList.size() > 0 && settingList.get(0).isActive()) {
						setting = settingList.get(0);
					}
					if (setting != null) {
						responseBean.setJournal(journalBean);
						JSONObject sessionKeyJson = new JSONObject();
						sessionKeyJson.put("role", "journal");
						sessionKeyJson.put("key", journal.getJournalId());
						sessionKeyJson.put("value", requestBean.getJournalPassword());
						SessionKeys sessionKeys = new SessionKeys();
						sessionKeys.setUserId(journal.getJournalId());
						sessionKeys.setSessionKey(sessionKeyJson.toString());
						sessionKeys.setKeyAlive(1);
						session.persist(sessionKeys);
						int sesssionKeyId = sessionKeys.getSessionId();
						String sessionKeyString = sesssionKeyId + "." + sessionKeyJson.toString();
						String sessionKeyB64 = Base64.getEncoder().encodeToString(sessionKeyString.getBytes());
						log.debug("Session Key : " + sessionKeyB64);
						responseBean.setAuthKey(sessionKeyB64);

						settingBean.setId(setting.getJournalSettingId());
						settingBean.setJournalId(setting.getJournalId());
						settingBean.setLanguageId(setting.getLanguageId());
						settingBean.setApplnName(setting.getApplicationName());
						settingBean.setHostPort(setting.getHostPort());
						settingBean.setHostUrl(setting.getHostURL());
						settingBean.setSuName(setting.getSuNAme());
						settingBean.setSpwd(setting.getsPWD());
						settingBean.setOutputUrlHls(setting.getOutputUrlHls());
						settingBean.setOutputUrlRtsp(setting.getOutputUrlRtsp());
						settingBean.setStreamName(setting.getStreamName());
						settingBean.setRecord(setting.isRecord());
						settingBean.setUpload(setting.isUpload());
						settingBean.setRecordUserName(setting.getRecord_user_name());
						settingBean.setRecordPassword(setting.getRecord_password());
						settingBean.setFramesPerSecond(setting.getFrames_per_second());
						settingBean.setKeyFrameInterval(setting.getKeyframe_interval());
						settingBean.setVideoBitrate(setting.getVideo_bitrate());
						settingBean.setAudioBitrate(setting.getAudio_bitrate());
						settingBean.setVideoFrameWidth(setting.getVideo_frame_width());
						settingBean.setVideoFrameHeight(setting.getVideo_frame_height());
						settingBean.setAbr(setting.isAbr());
						settingBean.setEnableSocialMedia(setting.isEnableSocialMedia());						
						if (journalDevicesList != null && journalDevicesList.size() > 0)
							responseBean.setJournalSetting(settingBean);

					}
					Criteria criteria = session.createCriteria(BroadcasterVideoNew.class);
					Criterion channelIdCriterion = Restrictions.eq("broadcasterChannelId",
							journal.getJournalChannelId());
					Criterion isLiveCriterion = Restrictions.eq("isLive", true);
					LogicalExpression logExp2 = Restrictions.and(channelIdCriterion, isLiveCriterion);
					criteria.add(logExp2);
					List<BroadcasterVideoNew> videoList = criteria.list();
					log.info("video size: " + videoList.size());
					if (videoList.size() == 1) {
						for (BroadcasterVideoNew video : videoList) {
							liveSettingsBean.setVideoId(video.getId());
							liveSettingsBean.setJournalId(journal.getJournalId());
							liveSettingsBean.setCurrentFBStreamKey(video.getFbStreamkey());
							liveSettingsBean.setCurrentHAStreamKey(video.getHaStreamkey());
							liveSettingsBean.setCurrentPSStreamKey(video.getPsStreamkey());
							liveSettingsBean.setCurrentYTStreamKey(video.getYtStreamkey());
						}
						responseBean.setLiveSettings(liveSettingsBean);
					}
				}
			}
			transaction.commit();
		} catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("journal login failed: " + re);
			throw re;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return responseBean;
	}

	public boolean isJournalActive(int journalId) {

		boolean isJournalActive = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria journalCriteria = session.createCriteria(Journal.class);
			journalCriteria.add(Restrictions.eq("id", journalId));
			journalCriteria.add(Restrictions.eq("isActive", true));
			List<Journal> journalList = journalCriteria.list();
			isJournalActive = journalList != null && journalList.size() > 0;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("authenticating journal failed :" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isJournalActive;
	}

	public boolean authenticateJournal(String sessionKey, int sessionId) {
		log.debug("authenticating journal");
		boolean isJournalAuthorized = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();

			JSONObject sessionJson = new JSONObject(sessionKey);
			int id = sessionJson.getInt("key");

			SessionKeys sessionKeys = (SessionKeys) session.get("com.limitless.services.engage.dao.SessionKeys",
					sessionId);
			if (sessionKeys != null) {
				if (sessionKeys.getKeyAlive() == 1 && sessionKeys.getUserId() == id) {
					isJournalAuthorized = true;
				} else {
					isJournalAuthorized = false;
				}
			} else {
				isJournalAuthorized = false;
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("authenticating journal failed :" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isJournalAuthorized;
	}

	public JournalLiveSettingsBean getJournalLiveSetting(int journalId) {
		JournalLiveSettingsBean settingBean = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Journal journal = (Journal) session.get("com.limitless.services.engage.journals.dao.Journal", journalId);
			if (journal != null) {
				int channelId = journal.getJournalChannelId();
				Criteria criteria = session.createCriteria(BroadcasterVideoNew.class);
				Criterion channelIdCriterion = Restrictions.eq("broadcasterChannelId", channelId);
				Criterion isLiveCriterion = Restrictions.eq("isLive", true);
				LogicalExpression logExp = Restrictions.and(channelIdCriterion, isLiveCriterion);
				criteria.add(logExp);
				List<BroadcasterVideoNew> videoList = criteria.list();
				log.info("video size: " + videoList.size());
				if (videoList.size() == 1) {
					settingBean = new JournalLiveSettingsBean();
					for (BroadcasterVideoNew video : videoList) {
						settingBean.setVideoId(video.getId());
						settingBean.setCurrentFBStreamKey(video.getFbStreamkey());
						settingBean.setCurrentYTStreamKey(video.getYtStreamkey());
						settingBean.setCurrentHAStreamKey(video.getHaStreamkey());
						settingBean.setCurrentPSStreamKey(video.getPsStreamkey());
						settingBean.setJournalId(journalId);
					}
				}
			}
			transaction.commit();
		} catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("getting journal FB Live settings failed :" + re);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return settingBean;
	}

	public JournalLiveSettingsBean updateStreamKey(String destination, JournalLiveSettingsBean requestBean) {
		JournalLiveSettingsBean settingsBean = new JournalLiveSettingsBean();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			BroadcasterVideoNew video = (BroadcasterVideoNew) session.get(
					"com.limitless.services.engage.entertainment.dao.BroadcasterVideoNew", requestBean.getVideoId());
			if (destination.equals("fb")) {
				video.setFbStreamkey(requestBean.getNewFBStreamKey());
			} else if (destination.equals("yt")) {
				video.setYtStreamkey(requestBean.getNewYTStreamKey());
			} else if (destination.equals("ha")) {
				video.setHaStreamkey(requestBean.getNewHAStreamKey());
			} else if (destination.equals("ps")) {
				video.setPsStreamkey(requestBean.getNewPSStreamKey());
			}
			session.update(video);
			settingsBean.setVideoId(video.getId());
			settingsBean.setJournalId(requestBean.getJournalId());
			transaction.commit();
		} catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("updating journal Live settings failed :" + re);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return settingsBean;
	}

	public String getJournalVersion() {
		log.debug("Getting Journal Version");
		String journalVersion = "1.0.01";
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			JournalVersion version = (JournalVersion) session
					.get("com.limitless.services.engage.journals.dao.JournalVersion", 1);
			if (version != null)
				journalVersion = version.getVersion();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			journalVersion = "1.0.01";
			log.error("Getting journal version failed :" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return journalVersion.trim();
	}

}
