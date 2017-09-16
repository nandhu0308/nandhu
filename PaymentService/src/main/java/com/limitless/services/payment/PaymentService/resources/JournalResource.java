package com.limitless.services.payment.PaymentService.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.limitless.services.engage.journals.JournalBean;
import com.limitless.services.engage.journals.JournalLoginRequestBean;
import com.limitless.services.engage.journals.JournalLoginResponseBean;
import com.limitless.services.engage.journals.NewJournalResponseBean;
import com.limitless.services.engage.journals.dao.JournalManager;

@Path("/journal")
public class JournalResource {
	final static Logger logger = Logger.getLogger(JournalResource.class);
	
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
	public JournalLoginResponseBean journalLogin(JournalLoginRequestBean requestBean) throws Exception {
		JournalLoginResponseBean responseBean = new JournalLoginResponseBean();
		try {
			JournalManager manager = new JournalManager();
			responseBean = manager.journalLogin(requestBean);
		} catch (Exception e) {
			logger.error("API Error", e);
			throw new Exception("Internal Server Error");
		}
		return responseBean;
	}
}
