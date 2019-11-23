package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.http.UploadVideoSegmentRequest;
import atropos.videolibraryapp.http.UploadVideoSegmentResponse;

public class UploadSegmentHandler implements RequestHandler<UploadVideoSegmentRequest, UploadVideoSegmentResponse>{
	
	LambdaLogger logger;
	
	//helpers
	
	
	@Override
	public UploadVideoSegmentResponse handleRequest(UploadVideoSegmentRequest uvsr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of UploadVideoSegment");
		logger.log(uvsr.toString());
		
		Boolean fail = false;
		String failMessage = "";
		String successResponse = "";
		
		//logic
		
		UploadVideoSegmentResponse uvsresp;
		if (fail) {
			uvsresp = new UploadVideoSegmentResponse(400, failMessage); // fail
		} else {
			uvsresp = new UploadVideoSegmentResponse(successResponse, 200);  // success
		}
		return uvsresp; 
	}

}
