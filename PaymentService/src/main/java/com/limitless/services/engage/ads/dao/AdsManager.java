package com.limitless.services.engage.ads.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.limitless.services.engage.ads.AdEventsBean;
import com.limitless.services.engage.ads.AssignLogoAdBean;
import com.limitless.services.payment.PaymentService.util.HibernateUtil;
import com.limitless.services.payment.PaymentService.util.RestClientUtil;
import com.sun.jersey.api.client.Client;

public class AdsManager {
	private static final Log log = LogFactory.getLog(AdsManager.class);
	Client client = RestClientUtil.createClient();
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public TimeZone getIndianTimeZone() {
		TimeZone indianTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
		if (indianTimeZone == null)
			indianTimeZone = TimeZone.getTimeZone("Asia/Calcutta");
		return indianTimeZone;
	}

	public AdEventsBean getAdEventByChannel(int channelId) throws Exception {
		AdEventsBean eventBean = new AdEventsBean();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(getIndianTimeZone());
			Date date = new Date();
			log.info(date);
			String today = sdf.format(date);
			
			Criteria criteria = session.createCriteria(AdEvents.class);
			Junction conditionGrp = Restrictions.conjunction().add(Restrictions.eq("date", today))
					.add(Restrictions.eq("channelId", channelId)).add(Restrictions.eq("isActive", true));
			criteria.add(conditionGrp);
			List<AdEvents> adEventList = criteria.list();
			log.info("ad event list: " + adEventList.size());
			if (adEventList.size() > 0) {
				for (AdEvents adEvent : adEventList) {
					String eventStartTimeString = today + " " + adEvent.getStartTime();
					String eventEndTimeString = today + " " + adEvent.getEndTime();
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					sdf2.setTimeZone(getIndianTimeZone());
					Date eventStartTime = sdf2.parse(eventStartTimeString);
					Date eventEndTime = sdf2.parse(eventEndTimeString);
					Date date2 = new Date();
					String currentTimeString = sdf2.format(date2);
					Date currentTime = sdf2.parse(currentTimeString);
					log.info("StartTime: " + eventStartTime + " # EndTime: " + eventEndTime + " # CurrentTime: "
							+ currentTime);
					if(currentTime.getTime() >= eventStartTime.getTime() && currentTime.getTime() < eventEndTime.getTime()) {
						eventBean.setActive(adEvent.isActive());
						eventBean.setAdWindowTime(adEvent.getAdWindowTime());
						eventBean.setChannelId(adEvent.getChannelId());
						eventBean.setDate(adEvent.getDate().toString());
						eventBean.setDuration(adEvent.getDuration());
						eventBean.setEndTime(adEvent.getEndTime());
						eventBean.setEventName(adEvent.getEventName());
						eventBean.setEventType(adEvent.getEventType());
						eventBean.setId(adEvent.getId());
						eventBean.setStartTime(adEvent.getStartTime());
					}
				}
			}
			transaction.commit();
		} catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("getting event failed: " + re);
			throw re;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return eventBean;
	}

	public List<AssignLogoAdBean> getLogoAd(int eventId) throws Exception {
		List<AssignLogoAdBean> logoAdsList = new ArrayList<AssignLogoAdBean>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			AdEvents events = (AdEvents) session.get("com.limitless.services.engage.ads.dao.AdEvents", eventId);
			if (events != null) {
				String today = events.getDate();
				Criteria criteria = session.createCriteria(AssignLogoAds.class);
				criteria.add(Restrictions.eq("adEventId", eventId));
				List<AssignLogoAds> assignLogoAdsList = criteria.list();
				log.info("logo ads size : " + assignLogoAdsList.size());
				if (assignLogoAdsList.size() > 0) {
					for (AssignLogoAds logoAds : assignLogoAdsList) {
						String startTimeString = today + " " + logoAds.getTimeSlotStart();
						String endTimeString = today + " " + logoAds.getTimeSlotEnd();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						sdf.setTimeZone(getIndianTimeZone());
						Date startTime = sdf.parse(startTimeString);
						Date endTime = sdf.parse(endTimeString);
						Date date = new Date();
						String currentTimeString = sdf.format(date);
						Date currentTime = sdf.parse(currentTimeString);
						log.info("StartTime: " + startTime + " # EndTime: " + endTime + " # CurrentTime: "
								+ currentTime);
						if (currentTime.getTime() >= startTime.getTime() && currentTime.getTime() < endTime.getTime()) {
							AssignLogoAdBean logoAdBean = new AssignLogoAdBean();
							logoAdBean.setAdEventId(eventId);
							logoAdBean.setAdPlacement(logoAds.getAdPlacement());
							logoAdBean.setAdTarget(logoAds.getAdTarget());
							logoAdBean.setId(logoAds.getId());
							logoAdBean.setLogoAdId(logoAds.getLogoAdId());
							logoAdBean.setLogoFtpPath(logoAds.getLogoFtpPath());
							logoAdBean.setTimeSlotStart(logoAds.getTimeSlotStart());
							logoAdBean.setTimeSlotEnd(logoAds.getTimeSlotEnd());
							logoAdBean.setImgName(logoAds.getImgName());
							logoAdBean.setLowerText(logoAds.getLowerText());
							logoAdBean.setStreamSource(logoAds.getStreamSource());
							logoAdBean.setAdType(logoAds.getAdType());
							logoAdsList.add(logoAdBean);
							logoAdBean = null;
						}
					}
				}
			}
			transaction.commit();
		} catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("getting ad failed: " + re);
			throw re;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return logoAdsList;
	}
}
