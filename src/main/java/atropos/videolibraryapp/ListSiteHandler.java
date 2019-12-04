package atropos.videolibraryapp;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SiteDAO;
import atropos.videolibraryapp.http.EmptyRequest;
import atropos.videolibraryapp.http.ListSitesResponse;
import atropos.videolibraryapp.model.Site;

public class ListSiteHandler implements RequestHandler<EmptyRequest, ListSitesResponse>{

	LambdaLogger logger;
	
	//helpers
	ArrayList<Site> getListSites() throws Exception{
		if (logger != null) { 
			logger.log("in getListSites"); 
		}
		SiteDAO dao = new SiteDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		return dao.getAllSites();
	}
	
	@Override
	public ListSitesResponse handleRequest(EmptyRequest er, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(er.toString());
		
		boolean fail = false;
		String failMessage = "";
		String successMessage = "";
		ArrayList<Site> sites = new ArrayList<Site>();
		
		//logic
		try {
			sites = getListSites();
			successMessage = "Success";
		}catch(Exception e){
			fail = true;
			failMessage = "SiteDAO failed";
		}
		
		ListSitesResponse lsr;
		if (fail) {
			lsr = new ListSitesResponse(400, failMessage);
		}else {
			lsr = new ListSitesResponse(sites, successMessage, 200);
		}
		
		return lsr;
	}
}
