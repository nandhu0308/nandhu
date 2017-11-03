package com.limitless.services.payment.PaymentService.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.limitless.service.common.Message;
import com.limitless.services.engage.journals.AuthResponseBean;
import com.limitless.services.engage.journals.JournalBean;
import com.limitless.services.engage.journals.JournalLiveSettingsBean;
import com.limitless.services.engage.journals.JournalLoginRequestBean;
import com.limitless.services.engage.journals.JournalLoginResponseBean;
import com.limitless.services.engage.journals.NewJournalResponseBean;
import com.limitless.services.engage.journals.dao.JournalManager;

@Path("/journal")
public class JournalResource {
	final static Logger logger = Logger.getLogger(JournalResource.class);

	@GET
	@Path("/getVersion")
	public Response getVersion() {
		logger.info("Reached Journal getVersion..");
		String version = "";

		try {
			JournalManager manager = new JournalManager();
			version = manager.getJournalVersion();
		} catch (Exception e) {
			logger.error("API Error", e);
		}

		return Response.status(200).entity(version).build();
	}

	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public NewJournalResponseBean newJournal(JournalBean bean) throws Exception {
		NewJournalResponseBean responseBean = new NewJournalResponseBean();
		try {
			JournalManager manager = new JournalManager();
			responseBean = manager.addJournal(bean);
		} catch (Exception e) {
			logger.error("API Error", e);
			throw new Exception("Internal Server Error");
		}
		return responseBean;
	}

	@Path("/jauth")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response journalLogin(JournalLoginRequestBean requestBean) throws Exception {
		JournalLoginResponseBean responseBean = new JournalLoginResponseBean();
		try {
			JournalManager manager = new JournalManager();
			responseBean = manager.journalLogin(requestBean);
		} catch (Exception e) {
			logger.error("API Error", e);

		}
		if (responseBean != null && responseBean.getJournal() != null && responseBean.getJournalSetting() != null) {
			return Response.status(200).entity(responseBean).build();
		}
		return Response.status(404).entity(new Message("Sorry. Cannot Login.Please check your credentials")).build();
	}

	@Path("/isactive/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response journalActive(@PathParam("id") int id) throws Exception {
		AuthResponseBean responseBean = new AuthResponseBean();
		try {
			JournalManager manager = new JournalManager();
			boolean result = manager.isJournalActive(id);
			responseBean.setResult(result);
		} catch (Exception e) {
			logger.error("API Error", e);
		}
		return Response.status(200).entity(responseBean).build();

	}

	@Path("/channel/ls/{journalId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JournalLiveSettingsBean getJournalChannelLive(@PathParam("journalId") int journalId) {
		JournalLiveSettingsBean settingsBean = new JournalLiveSettingsBean();
		try {
			JournalManager manager = new JournalManager();
			settingsBean = manager.getJournalLiveSetting(journalId);
		} catch (Exception e) {
			logger.error("API Error", e);
		}
		return settingsBean;
	}

	@Path("/channel/streamkey/{destination}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStreamKey(@PathParam("destination") String destination, JournalLiveSettingsBean requestBean) {
		boolean result = false;
		try {
			JournalManager manager = new JournalManager();
			manager.updateStreamKey(destination, requestBean);
			result = true;
		} catch (Exception e) {
			logger.error("API Error", e);
		}
		return getResponse(result);
	}

	private Response getResponse(boolean result) {
		return result ? Response.status(200).build() : Response.status(404).build();
	}

}
