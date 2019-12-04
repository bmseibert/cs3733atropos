package atropos.videolibraryapp;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SiteDAO;
import atropos.videolibraryapp.http.ChangeRegisterSiteRequest;
import atropos.videolibraryapp.http.UnregisterSiteResponse;
import atropos.videolibraryapp.model.Site;

public class UnregisterSiteHandler implements RequestHandler<ChangeRegisterSiteRequest, UnregisterSiteResponse>{
	
	LambdaLogger logger;
	//helpers
	boolean registerSite(Site site) throws Exception{
		if (logger != null) { 
			logger.log("in registerSite"); 
		}
		SiteDAO dao = new SiteDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		dao.unregisterSite(site);
		return true;
	}
	
	
	@Override
	public UnregisterSiteResponse handleRequest(ChangeRegisterSiteRequest crsr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(crsr.toString());
		
		boolean fail = false;
		String failMessage = "";
		String successMessage = "";
		String siteURL = crsr.getSite();
		Site site = new Site(siteURL);
		
		//logic
		try {
			registerSite(site);
			successMessage = "Success";
		}catch(Exception e) {
			fail = true;
			failMessage = "Unable to register site";
		}
		
		UnregisterSiteResponse rsr;
		if(fail) {
			rsr = new UnregisterSiteResponse(400, failMessage);
		}else {
			rsr = new UnregisterSiteResponse(successMessage, 200);
		}
		
		return rsr;
		
	}

}
