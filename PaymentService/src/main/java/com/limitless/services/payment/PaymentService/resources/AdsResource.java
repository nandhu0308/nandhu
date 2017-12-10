package com.limitless.services.payment.PaymentService.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.limitless.services.engage.ads.AdEventsBean;
import com.limitless.services.engage.ads.AssignLogoAdBean;
import com.limitless.services.engage.ads.VideoAdEventsBean;
import com.limitless.services.engage.ads.dao.AdsManager;

@Path("/ads")
public class AdsResource {
	final static Logger logger = Logger.getLogger(AdsResource.class);

	@Path("/get/event/channel/{channelId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAdEventByChannel(@PathParam("channelId") int channelId) throws Exception {
		AdEventsBean eventsBean = new AdEventsBean();
		try {
			AdsManager manager = new AdsManager();
			eventsBean = manager.getAdEventByChannel(channelId);
		} catch (Exception e) {
			logger.error("API Error", e);
		}
		return eventsBean != null ? Response.status(200).entity(eventsBean).build() : Response.status(404).build();

	}

	@Path("/get/logo/event/{eventId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AssignLogoAdBean> getLogoAdByEvent(@PathParam("eventId") int eventId) throws Exception {
		List<AssignLogoAdBean> logoAdBean = new ArrayList<AssignLogoAdBean>();
		try {
			AdsManager manager = new AdsManager();
			logoAdBean = manager.getLogoAd(eventId);
		} catch (Exception e) {
			logger.error("API Error", e);
			throw new Exception("Internal Server Error");
		}
		return logoAdBean;
	}
	
	@Path("/get/event/videoads/{channelId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<VideoAdEventsBean> getVideoAdsEvents(@PathParam("channelId") int channelId) throws Exception{
		List<VideoAdEventsBean> eventsBeanList = new ArrayList<VideoAdEventsBean>();
		try {
			AdsManager manager = new AdsManager();
			eventsBeanList = manager.getVideoAdEventsByChannel(channelId);
		} catch (Exception e) {
			logger.error("API Error", e);
			throw new Exception("Internal Server Error");
		}
		return eventsBeanList;
	}
}
