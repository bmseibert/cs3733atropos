package atropos.videolibraryapp;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.ListVideoSegmentsRequest;
import atropos.videolibraryapp.http.ListVideoSegmentsResponse;
import atropos.videolibraryapp.model.Segment;

public class ListSegmentsHandler implements RequestHandler<ListVideoSegmentsRequest, ListVideoSegmentsResponse>{
	
	LambdaLogger logger;
	
	//helpers
	ArrayList<Segment> getRemoteSegments() throws Exception{
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		SegmentsDAO dao = new SegmentsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		
	}
	
	ArrayList<Segment> getLocalSegments() throws Exception{
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
	}
	
	@Override 
	public ListVideoSegmentsResponse handleRequest(ListVideoSegmentsRequest lvsr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(lvsr.toString());
		
		Boolean fail = false;
		String failMessage = "";
		String successResponse = "";
		ArrayList<Segment> segs;
		
		//logic
		try {	
			if(lvsr.getIsRemote()) {
				getRemoteSegments();
			}else {
				getLocalSegments();
			}
		}catch(Exception e) {
			fail = true;
			failMessage = "Cannot connect to database";
		}
		
		ListVideoSegmentsResponse lvsresp;
		if(fail) {
			lvsresp = new ListVideoSegmentsResponse(400, failMessage);
		}else {
			lvsresp = new ListVideoSegmentsResponse(200, segs);
		}
		return lvsresp;
	}
}
