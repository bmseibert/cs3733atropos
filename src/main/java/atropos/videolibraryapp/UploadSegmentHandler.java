package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.UploadVideoSegmentRequest;
import atropos.videolibraryapp.http.UploadVideoSegmentResponse;
import atropos.videolibraryapp.model.Segment;

public class UploadSegmentHandler implements RequestHandler<UploadVideoSegmentRequest, UploadVideoSegmentResponse>{
	
	LambdaLogger logger;
	
	//helpers
	boolean IsDuplicate(String name) throws Exception {
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		SegmentsDAO dao = new SegmentsDAO();
		Segment exists = dao.getSegment(name);
		if(exists == null) {
			return false;
		}else {
			return true;
		}
	}
	
	void uploadSegment(String name, String character) throws Exception{
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		SegmentsDAO dao = new SegmentsDAO();
		Segment seg = new Segment(name, character);
		
		
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
		try {
			isDup = IsDuplicate(segmentName);
			try {
				if(!isDup) {
					
				}else {
					
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
