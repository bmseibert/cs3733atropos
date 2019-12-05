package atropos.videolibraryapp;

import java.io.FileReader;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.EmptyRequest;
import atropos.videolibraryapp.http.PublicVideoSegmentResponse;
import atropos.videolibraryapp.model.Segment;
import atropos.videolibraryapp.model.Segments;

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
	
	ArrayList<Segments> convertSegments(ArrayList<Segment> seg){
		ArrayList<Segments> output = new ArrayList<Segments>();
		
		for(int i = 0; i < seg.size(); i++) {
			Segment s = seg.get(i);
			Segments j = new Segments(s.getName(), s.getCharacter(), s.getUrl());
			output.add(j);
		}
		
		return output;
	}
	
	@Override
	public PublicVideoSegmentResponse handleRequest(EmptyRequest er, Context context) {
		
		boolean fail = false;
		ArrayList<Segment> list = new ArrayList<Segment>();
		ArrayList<Segments> new_list = new ArrayList<Segments>();
		String failMessage = "";
		
		//logic
		try {
			list = getAllPublicSegments();
		}catch(Exception e) {
			fail = true;
			failMessage = "Could not connect";
		}
		
		new_list = convertSegments(list);		
		
		PublicVideoSegmentResponse pvsr;
		if(fail) {
			pvsr = new PublicVideoSegmentResponse(400, failMessage);
		}else {
			pvsr = new PublicVideoSegmentResponse(new_list, 200);
		}
		
		return pvsr;
		
	}
}
