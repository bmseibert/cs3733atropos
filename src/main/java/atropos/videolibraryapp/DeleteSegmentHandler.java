package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.DeleteVideoSegmentRequest;
import atropos.videolibraryapp.http.DeleteVideoSegmentResponse;
import atropos.videolibraryapp.model.Segment;

public class DeleteSegmentHandler implements RequestHandler< DeleteVideoSegmentRequest, DeleteVideoSegmentResponse>{
		
	LambdaLogger logger;
	//Helpers
	public boolean isInDB(String name) throws Exception{
		if (logger != null) { 
			logger.log("in check for is in DB"); 
		}
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		Segment exist = dao.getSegment(name);
		
		if(exist == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void deleteFromDB(String name) throws Exception {
		if (logger != null) { 
			logger.log("in check for delete from db"); 
		}
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		Segment seg = new Segment(name);
		dao.deleteSegment(seg);
		
	}
	//Logic

	@Override
	public DeleteVideoSegmentResponse handleRequest(DeleteVideoSegmentRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of DeleteSegmentRequestHandler");
		logger.log(req.toString());
		
		boolean fail = false;
		boolean exists = false;
		
		String failMessage = "";
		String successMessage = "";
		
		// Check to see if in DB
		String segmentName = req.getSegment();
		try {	
			exists = isInDB(segmentName);
			if(exists) {
				try {
					deleteFromDB(segmentName);
					successMessage = "Success";
				}catch(Exception e){
					fail = true;
					failMessage = "Could not delete Segment";
				}
			}else {
				successMessage = "Does not exist";
			}
		} catch(Exception e){
			failMessage = "Cannot Connect to Database";
			fail = true;
		}
		
		
		DeleteVideoSegmentResponse response;
		if (fail) {
			response = new DeleteVideoSegmentResponse(400, failMessage);
		} else {
			response = new DeleteVideoSegmentResponse(successMessage, 200);  // success
		}

		return response; 
	}
		
}



