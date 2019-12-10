package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.ChangeMarkVideoRequest;
import atropos.videolibraryapp.http.UnmarkSegmentResponse;
import atropos.videolibraryapp.model.Segment;

public class UnmarkSegmentHandler implements RequestHandler<ChangeMarkVideoRequest, UnmarkSegmentResponse>{
	
	LambdaLogger logger;
	
	// helpers
	boolean unmarkVideoSegment(Segment segment, ChangeMarkVideoRequest req) throws Exception {
		if (logger != null) { 
			logger.log("in unmarkVideoSegment"); 
		}
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		segment.setIsMarked(false);
		dao.updateSegmentMark(segment);
		return true;
	}
	
	@Override
	public UnmarkSegmentResponse handleRequest(ChangeMarkVideoRequest cmvr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		
		boolean fail = false;
		String failMessage = "";
		String successMessage = "";
		String segmentName = cmvr.getSegment();
		Segment newSeg = new Segment(segmentName);

		//logic
		try {
			unmarkVideoSegment(newSeg, cmvr);
			successMessage = "Success";
		}catch (Exception e){
			fail = true;
			failMessage = "Failed to update marked status: " + cmvr.getSegment() + " : " + cmvr.getMarked();
		}
		
		UnmarkSegmentResponse usr;
		if(fail) {
			usr = new UnmarkSegmentResponse(failMessage, 400);
		}else {
			usr = new UnmarkSegmentResponse(200, successMessage);
		}
		return usr;
	}

}
