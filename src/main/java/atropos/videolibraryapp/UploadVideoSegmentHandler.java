package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.UploadVideoSegmentRequest;
import atropos.videolibraryapp.http.UploadVideoSegmentResponse;
import atropos.videolibraryapp.model.Segment;

public class UploadVideoSegmentHandler implements RequestHandler<UploadVideoSegmentRequest, UploadVideoSegmentResponse>{
	
	LambdaLogger logger;
	
	//helpers
	boolean IsDuplicate(String name) throws Exception {
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		Segment exists = dao.getSegment(name);
		if(exists == null) {
			return false;
		}else {
			return true;
		}
	}
	
	void uploadSegment(String name, String character, String url) throws Exception{
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		Segment seg = new Segment(name, character, url);
		dao.addSegment(seg);
		
	}
	
	
	@Override
	public UploadVideoSegmentResponse handleRequest(UploadVideoSegmentRequest uvsr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of UploadVideoSegment");
		logger.log(uvsr.toString());
		
		boolean isDup = false;
		boolean fail = false;
		String failMessage = "";
		String successResponse = "";
		
		//logic
		String segmentName = uvsr.getName();
		String charName = uvsr.getCharacter();
		String url = uvsr.getURL();
		try {
			isDup = IsDuplicate(segmentName);
			try {
				if(!isDup) {
					uploadSegment(segmentName, charName, url);
					successResponse = "Segment Created";
				}else {
					successResponse = "Already Exists";
				}
			}catch(Exception e) {
				fail = true;
				failMessage = "Unable to Upload Video Segment";
			}
		}catch (Exception e) {
			fail = true;
			failMessage = "Unable to Connect to Database";
		}
		
		UploadVideoSegmentResponse uvsresp;
		if (fail) {
			uvsresp = new UploadVideoSegmentResponse(400, failMessage); // fail
		} else {
			uvsresp = new UploadVideoSegmentResponse(successResponse, 200);  // success
		}
		return uvsresp; 
	}

}
