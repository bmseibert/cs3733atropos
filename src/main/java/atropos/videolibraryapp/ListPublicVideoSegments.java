package atropos.videolibraryapp;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.EmptyRequest;
import atropos.videolibraryapp.http.PublicVideoSegmentResponse;
import atropos.videolibraryapp.model.Segment;

public class ListPublicVideoSegments implements RequestHandler<EmptyRequest, PublicVideoSegmentResponse >{
	
	LambdaLogger logger;
	//helpers
	ArrayList<Segment> getAllPublicSegments() throws Exception{
		if (logger != null) { 
			logger.log("in getAllPublicSegments"); 
		}
		SegmentsDAO dao = new SegmentsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		return dao.getAllSegmentsByMarkRemote(false, false);
	}
	
	@Override
	public PublicVideoSegmentResponse handleRequest(EmptyRequest er, Context context) {
		
		boolean fail = false;
		ArrayList<Segment> list = new ArrayList<Segment>();
		String failMessage = "";
		
		//logic
		try {
			list = getAllPublicSegments();
		}catch(Exception e) {
			fail = true;
			failMessage = "Could not connect";
		}
		
		
		
//		// parsing file "JSONExample.json" 
//        Object obj = new JSONParser().parse(list); 
//          
//        // typecasting obj to JSONObject 
//        JSONObject jo = (JSONObject) obj; 
//		
		
		PublicVideoSegmentResponse pvsr;
		if(fail) {
			pvsr = new PublicVideoSegmentResponse(400, failMessage);
		}else {
			pvsr = new PublicVideoSegmentResponse(list, 200);
		}
		
		return pvsr;
		
	}
}
