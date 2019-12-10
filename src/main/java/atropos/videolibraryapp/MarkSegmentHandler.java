package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.ChangeMarkVideoRequest;
import atropos.videolibraryapp.http.MarkSegmentResponse;
import atropos.videolibraryapp.model.Segment;

public class MarkSegmentHandler implements RequestHandler<ChangeMarkVideoRequest, MarkSegmentResponse>{
	
	LambdaLogger logger;
	
	// helpers
	boolean markVideoSegment(Segment segment, ChangeMarkVideoRequest req) throws Exception {
		if (logger != null) { 
			logger.log("in unmarkVideoSegment"); 
		}
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		segment.setIsMarked(req.getMarked());
		dao.updateSegmentMark(segment);
		return true;
	}
	
	@Override
	public MarkSegmentResponse handleRequest(ChangeMarkVideoRequest cmvr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		
		boolean fail = false;
		String failMessage = "";
		String successMessage = "";
		String segmentName = cmvr.getSegment();
		Segment newSeg = new Segment(segmentName);
		newSeg.toggleIsMarked();

		//logic
		try {
			markVideoSegment(newSeg, cmvr);
			successMessage = "Success";
		}catch (Exception e){
			fail = true;
			failMessage = "Failed to update marked status";
		}
		
		MarkSegmentResponse usr;
		if(fail) {
			usr = new MarkSegmentResponse(400, failMessage);
		}else {
			usr = new MarkSegmentResponse(successMessage, 200);
		}
		return usr;
	}

}
