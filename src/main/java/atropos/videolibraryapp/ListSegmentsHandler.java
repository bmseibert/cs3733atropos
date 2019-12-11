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
			logger.log("in getRemoteSegments"); 
		}
		SegmentsDAO dao = new SegmentsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		ArrayList<Segment> segs = dao.getAllSegments();
		ArrayList<Segment> remoteSegs = new ArrayList<Segment>();
		for(int i=0; i<segs.size(); i++) {
			if(segs.get(i).getIsRemote() == true) {
				remoteSegs.add(segs.get(i));
			}
		}
		return remoteSegs;
	}
	
	ArrayList<Segment> getLocalSegments() throws Exception{
		if (logger != null) { 
			logger.log("in getLocalSegments"); 
		}
		SegmentsDAO dao = new SegmentsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		ArrayList<Segment> segs = dao.getAllSegments();
		ArrayList<Segment> localSegs = new ArrayList<Segment>();
		for(int i=0; i<segs.size(); i++) {
			if(segs.get(i).getIsRemote() == false) {
				localSegs.add(segs.get(i));
			}
		}
		return localSegs;
	}
	
	@Override 
	public ListVideoSegmentsResponse handleRequest(ListVideoSegmentsRequest lvsr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(lvsr.toString());
		
		boolean fail = false;
		String failMessage = "";
		String passMessage = "";
		ArrayList<Segment> segs = null;
		
		//logic
		try {	
			if(lvsr.getIsRemote()) {
				segs = getRemoteSegments();
				passMessage = "Success";
			}else {
				segs = getLocalSegments();
				passMessage = "Success";
			}
		}catch(Exception e) {
			fail = true;
			failMessage = "Cannot connect to database";
		}
		
		ListVideoSegmentsResponse lvsresp;
		if(fail) {
			lvsresp = new ListVideoSegmentsResponse(400, failMessage);
		}else {
			lvsresp = new ListVideoSegmentsResponse(200, segs, passMessage);
		}
		return lvsresp;
	}
}
