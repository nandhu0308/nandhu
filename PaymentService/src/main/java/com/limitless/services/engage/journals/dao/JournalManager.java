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
import com.limitless.services.engage.journals.JournalDeviceBean;
import com.limitless.services.engage.journals.JournalLoginRequestBean;
import com.limitless.services.engage.journals.JournalLoginResponseBean;
import com.limitless.services.engage.journals.JournalSettingBean;
import com.limitless.services.engage.journals.NewJournalResponseBean;
import com.limitless.services.payment.PaymentService.util.HibernateUtil;
import com.limitless.services.payment.PaymentService.util.RestClientUtil;
import com.sun.jersey.api.client.Client;

public class JournalManager {
	private static final Log log = LogFactory.getLog(JournalManager.class);
	Client client = RestClientUtil.createClient();
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public NewJournalResponseBean addJournal(JournalBean bean) {
		NewJournalResponseBean responseBean = new NewJournalResponseBean();
		Session session =  null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			
			Journal journal = new Journal();
			journal.setJournalChannelId(bean.getJournalChannelId());
			journal.setJournalEmail(bean.getJournalEmail());
			journal.setJournalPassword(bean.getJournalPassword());
			journal.setJournalEmpId(bean.getJournalEmpId());
			journal.setJournalFirstName(bean.getJournalFirstName());
			journal.setJournalLastName(bean.getJournalLastName());
			journal.setJournalMobile(bean.getJournalMobile());
			journal.setJournalIsActive(bean.isJournalIsActive());
			journal.setJournalIsDeleted(bean.isJournalIsDeleted());
			journal.setJournalCreatedBy(bean.getJournalCreatedBy());
			journal.setJournalUpdatedBy(bean.getJournalUpdatedBy());
			session.persist(journal);
			
			responseBean.setJournalId(journal.getJournalId());
			responseBean.setMessage("Success");
			
			transaction.commit();
		} catch(RuntimeException re) {
			if(transaction != null) {
				transaction.rollback();
			}
			log.error("adding journal failed: " + re);
			throw re;
		} finally {
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}
		return responseBean;
	}
	
	public JournalLoginResponseBean journalLogin(JournalLoginRequestBean requestBean) {
		JournalLoginResponseBean responseBean = new JournalLoginResponseBean();
		List<JournalDeviceBean> deviceList = new ArrayList<JournalDeviceBean>();
		JournalSettingBean settingBean = new JournalSettingBean();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Journal.class);
			Criterion emailCriterion = Restrictions.eq("journalEmail", requestBean.getJournalEmail());
			Criterion passwdCriterion = Restrictions.eq("journalPassword", requestBean.getJournalPassword());
			LogicalExpression logExp = Restrictions.and(emailCriterion, passwdCriterion);
			criteria.add(logExp);
			List<Journal> journalList = criteria.list();
			log.debug("journal list : " + journalList.size());
			if(journalList.size() == 1) {
				for(Journal journal : journalList) {
					responseBean.setJournalId(journal.getJournalId());
					responseBean.setJournalChannelId(journal.getJournalChannelId());
					responseBean.setJournalEmail(journal.getJournalEmail());
					responseBean.setJournalFirstName(journal.getJournalFirstName());
					responseBean.setJournalLastName(journal.getJournalLastName());
					responseBean.setJournalMobile(journal.getJournalMobile());
					responseBean.setJournalEmpId(journal.getJournalEmpId());
					responseBean.setJournalIsActive(journal.isJournalIsActive());
					responseBean.setJournalIsDeleted(journal.isJournalIsDeleted());
					responseBean.setJournalCreatedBy(journal.getJournalCreatedBy());
					responseBean.setJournalUpdatedBy(journal.getJournalUpdatedBy());
					Criteria criteria2 = session.createCriteria(JournalDevices.class);
					Criterion jidCriterion = Restrictions.eq("JOURNAL_ID", journal.getJournalId());
					Criterion macCriterion = Restrictions.eq("journalDeviceMacId", requestBean.getJournalDeviceMacId());
					LogicalExpression logExp2 = Restrictions.and(jidCriterion, macCriterion);
					List<JournalDevices> journalDevicesList = criteria2.list();
					log.debug("journal devices size : " +journalDevicesList.size());
					if(journalDevicesList.size()>0) {
						for(JournalDevices devices: journalDevicesList) {
							JournalDeviceBean deviceBean = new JournalDeviceBean();
							deviceBean.setJournalDeviceId(devices.getJournalDeviceId());
							deviceBean.setJournalId(devices.getJournalId());
							deviceBean.setJournalDeviceMacId(devices.getJournalDeviceMacId());
							deviceBean.setJournalDeviceIsActive(devices.isJournalDeviceIsActive());
							deviceBean.setCreatedBy(devices.getCreatedBy());
							deviceBean.setUpdatedBy(devices.getUpdatedBy());
							deviceList.add(deviceBean);
							deviceBean = null;
						}
						responseBean.setJournalDeviceList(deviceList);
					}
					Criteria criteria3 = session.createCriteria(JournalSetting.class);
					criteria3.add(Restrictions.eq("journalId", journal.getJournalId()));
					List<JournalSetting> settingList = criteria3.list();
					log.debug("setting list size : " + settingList.size());
					if(settingList.size() > 0) {
						for(JournalSetting setting : settingList) {
							settingBean.setJournalSettingId(setting.getJournalSettingId());
							settingBean.setJournalId(setting.getJournalId());
							settingBean.setLanguageId(setting.getLanguageId());
							settingBean.setApplicationName(setting.getApplicationName());
							settingBean.setHostPort(setting.getHostPort());
							settingBean.setHostUrl(setting.getHostURL());
							settingBean.setSuName(setting.getSuNAme());
							settingBean.setsPWD(setting.getsPWD());
							settingBean.setOutputUrlHls(setting.getOutputUrlHls());
							settingBean.setOutputUrlRtsp(setting.getOutputUrlRtsp());
							settingBean.setActive(setting.isActive());
							settingBean.setRecord(setting.isRecord());
							settingBean.setUpload(setting.isUpload());
							settingBean.setCreatedBy(setting.getCreatedBy());
							settingBean.setUpdatedBy(setting.getUpdatedBy());
							responseBean.setJournalSetting(settingBean);
						}
					}
				}
			}
			transaction.commit();
		} catch(RuntimeException re) {
			if(transaction != null) {
				transaction.rollback();
			}
			log.error("journal login failed: " + re);
			throw re;
		} finally {
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}
		return responseBean;
	}
	
}
