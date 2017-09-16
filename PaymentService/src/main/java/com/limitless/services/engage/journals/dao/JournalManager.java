package com.limitless.services.engage.journals.dao;

import java.util.ArrayList;
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

import com.limitless.services.engage.journals.JournalBean;

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
					journalBean.setfName (journal.getJournalFirstName());
					journalBean.setlName(journal.getJournalLastName());
					journalBean.setMobile(journal.getJournalMobile());
					journalBean.setEmpId(journal.getJournalEmpId());					
					responseBean.setJournal(journalBean);
					Criteria deviceCriteria = session.createCriteria(JournalDevices.class);
					Criterion jidCriterion = Restrictions.eq("journalId", journal.getJournalId());
					Criterion macCriterion = Restrictions.eq("journalDeviceMacId", requestBean.getJournalDeviceMacId());
					LogicalExpression logExp2 = Restrictions.and(jidCriterion, macCriterion);
					deviceCriteria.add(logExp2);
					deviceCriteria.add(Restrictions.eq("isActive", true));
					List<JournalDevices> journalDevicesList = deviceCriteria.list();
					log.debug("journal devices size : " + journalDevicesList.size());

					if (journalDevicesList.size() > 0) {
						Criteria settingsCriteria = session.createCriteria(JournalSetting.class);
						settingsCriteria.add(Restrictions.eq("journalId", journal.getJournalId()));
						settingsCriteria.add(Restrictions.eq("isActive", true));
						List<JournalSetting> settingList = settingsCriteria.list();
						log.debug("setting list size : " + settingList.size());
						if (settingList.size() > 0) {
							for (JournalSetting setting : settingList) {
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
								
								responseBean.setJournalSetting(settingBean);
							}
						}
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

}
