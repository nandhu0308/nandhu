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
import org.json.JSONArray;
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
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JournalManager {
	private static final Log log = LogFactory.getLog(JournalManager.class);
	Client client = RestClientUtil.createClient();
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static String WOWZA_HOST = "http://journal2.haappyapp.com:8087/v2/servers/_defaultServer_/vhosts/_defaultVHost_/applications/";

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
						liveSettingsBean.setJournalId(journal.getJournalId());
						liveSettingsBean.setCurrentFBStreamKey(setting.getFbStreamkey());
						liveSettingsBean.setCurrentPSStreamKey(setting.getPsStreamkey());
						liveSettingsBean.setCurrentYTStreamKey(setting.getYtStreamkey());
						liveSettingsBean.setFbPageId(setting.getFb_page_id());
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
				// int channelId = journal.getJournalChannelId();
				Criteria criteria = session.createCriteria(JournalSetting.class);
				Criterion journalIdCriterion = Restrictions.eq("journalId", journalId);
				Criterion isActiveCriterion = Restrictions.eq("isActive", true);
				LogicalExpression logExp = Restrictions.and(journalIdCriterion, isActiveCriterion);
				criteria.add(logExp);
				List<JournalSetting> settingList = criteria.list();
				log.info("setting list size: " + settingList.size());
				if (settingList.size() == 1) {
					settingBean = new JournalLiveSettingsBean();
					for (JournalSetting setting : settingList) {
						settingBean.setJournalId(journal.getJournalId());
						settingBean.setCurrentFBStreamKey(setting.getFbStreamkey());
						settingBean.setCurrentPSStreamKey(setting.getPsStreamkey());
						settingBean.setCurrentYTStreamKey(setting.getYtStreamkey());
						settingBean.setFbPageId(setting.getFb_page_id());
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
			Journal journal = (Journal) session
					.get("com.limitless.services.engage.journals.dao.Journal", requestBean.getJournalId());
			if (journal != null) {
				Criteria criteria = session.createCriteria(JournalSetting.class);
				Criterion jidCriterion = Restrictions.eq("journalId", requestBean.getJournalId());
				Criterion isActive = Restrictions.eq("isActive", true);
				LogicalExpression logExp = Restrictions.and(jidCriterion, isActive);
				criteria.add(logExp);
				List<JournalSetting> settingList = criteria.list();
				log.info("setting list size: " + settingList.size());
				if(settingList.size() == 1) {
					for(JournalSetting setting: settingList) {
						JournalSetting instance = (JournalSetting) session
								.get("com.limitless.services.engage.journals.dao.JournalSetting", setting.getJournalSettingId());
						if (destination.equals("fb")) {
							instance.setFbStreamkey(requestBean.getNewFBStreamKey());
							session.update(instance);
							settingsBean.setJournalId(requestBean.getJournalId());
							wowzaStreamTargetUpdater(instance.getApplicationName(), "facebook", instance.getFbStreamkey());
						} else if (destination.equals("yt")) {
							instance.setYtStreamkey(requestBean.getNewYTStreamKey());
							session.update(setting);
							settingsBean.setJournalId(requestBean.getJournalId());
							wowzaStreamTargetUpdater(instance.getApplicationName(), "youtube", instance.getYtStreamkey());
						} else if (destination.equals("ps")) {
							instance.setPsStreamkey(requestBean.getNewPSStreamKey());
							session.update(setting);
							settingsBean.setJournalId(requestBean.getJournalId());
							wowzaStreamTargetUpdater(instance.getApplicationName(), "periscope", instance.getPsStreamkey());
						}
					}
				}
			}
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
	
	public void wowzaStreamTargetUpdater(String applicationName, String destination, String newStreamKey) {
		WebResource webResource = client
				.resource(WOWZA_HOST+applicationName+"/pushpublish/mapentries");
		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		String stringResponse = clientResponse.getEntity(String.class);
		log.info("wowza response: "+ stringResponse);
		JSONObject getStreamTargetJson = new JSONObject(stringResponse);
		JSONArray existingStreamTargets = getStreamTargetJson.getJSONArray("mapEntries");
		log.info("existing streams: " + existingStreamTargets);
		if(existingStreamTargets.length() > 0) {
			for(int i=0; i<existingStreamTargets.length(); i++) {
				JSONObject mapEntry = existingStreamTargets.getJSONObject(i);
				if(mapEntry.getString("host").equals("rtmp-api.facebook.com")) {
					String oldEntryName = mapEntry.getString("entryName");
					mapEntry.put("entryName", applicationName+"-facebook-"+System.currentTimeMillis());
					mapEntry.put("streamName", newStreamKey);
					log.info("new stream target json: "+ mapEntry.toString());
					WebResource createStreamTargetWebResource = client
							.resource(WOWZA_HOST+applicationName+"/pushpublish/mapentries/"+applicationName+"-facebook-"+System.currentTimeMillis());
					ClientResponse createStreamTargetClientResponse = createStreamTargetWebResource.accept("application/json")
							.type("application/json")
							.post(ClientResponse.class, mapEntry.toString());
					String createStreamTargetResponseString = createStreamTargetClientResponse.getEntity(String.class);
					log.info("stream target creation response: "+ createStreamTargetResponseString);
					JSONObject createStreamTargetResponseJson = new JSONObject(createStreamTargetResponseString);
					if(createStreamTargetResponseJson.getBoolean("success")) {
						WebResource deleteStreamTargetWebResource = client
								.resource(WOWZA_HOST+applicationName+"/pushpublish/mapentries/"+oldEntryName);
						ClientResponse deleteStreamTargetResponseClientResponse = deleteStreamTargetWebResource.accept("application/json")
								.type("application/json")
								.delete(ClientResponse.class);
						String deleteStreamTargetResponseString = deleteStreamTargetResponseClientResponse.getEntity(String.class);
						log.info("stream target delete response: "+ deleteStreamTargetResponseString);
						JSONObject deleteStreamTargetResponseJson = new JSONObject(deleteStreamTargetResponseString);
						if(deleteStreamTargetResponseJson.getBoolean("success")) {
							log.info("stream target cycle success");
						} else {
							log.info("stream target cycle failed");
						}
					}
				}
			}
		}
	}
}
