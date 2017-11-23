package com.limitless.services.payment.PaymentService.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.limitless.services.engage.ads.AdEventsBean;
import com.limitless.services.engage.ads.AssignLogoAdBean;
import com.limitless.services.engage.ads.dao.AdsManager;

@Path("/ads")
public class AdsResource {
	final static Logger logger = Logger.getLogger(AdsResource.class);
	
	@Path("/get/event/channel/{channelId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AdEventsBean> getAdEventByChannel (@PathParam("channelId") int channelId) throws Exception{
		List<AdEventsBean> eventsList = new ArrayList<AdEventsBean>();
		try {
			AdsManager manager = new AdsManager();
			eventsList = manager.getAdEventByChannel(channelId);
		} catch(Exception e) {
			logger.error("API Error", e);
			throw new Exception("Internal Server Error");
		}
		return eventsList;
	}
	
	@Path("/get/logo/event/{eventId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AssignLogoAdBean> getLogoAdByEvent (@PathParam("eventId") int eventId) throws Exception{
		List<AssignLogoAdBean> logoAdBean = new ArrayList<AssignLogoAdBean>();
		try {
			AdsManager manager = new AdsManager();
			logoAdBean = manager.getLogoAd(eventId);
		} catch(Exception e) {
			logger.error("API Error", e);
			throw new Exception("Internal Server Error");
		}
		return logoAdBean;
	}
}
