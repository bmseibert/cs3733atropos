package atropos.videolibraryapp;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.http.EmptyRequest;
import atropos.videolibraryapp.http.PublicVideoSegmentResponse;
import atropos.videolibraryapp.model.Segment;

public class ListPublicVideoSegments implements RequestHandler<EmptyRequest, PublicVideoSegmentResponse >{
	
	LambdaLogger logger;
	//helpers
	boolean getAllPublicSegments() throws Exception{
		
		return true;
	}
	
	@Override
	public PublicVideoSegmentResponse handleRequest(EmptyRequest er, Context context) {
		
		boolean fail = false;
		ArrayList<Segment> list = new ArrayList<Segment>();
		String failMessage = "";
		
		//logic
		try {
			
		}catch(Exception e) {
			fail = true;
			failMessage = "Could not connect";
		}
		
		PublicVideoSegmentResponse pvsr;
		if(fail) {
			pvsr = new PublicVideoSegmentResponse(list, 200);
		}else {
			pvsr = new PublicVideoSegmentResponse(400, failMessage);
		}
		
		return pvsr;
		
	}
}
