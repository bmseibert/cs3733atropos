package atropos.videolibraryapp;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;
import atropos.videolibraryapp.http.SearchVideoSegmentRequest;
import atropos.videolibraryapp.http.SearchVideoSegmentsResponse;
import atropos.videolibraryapp.model.Segment;


public class SearchSegmentsHandler implements RequestHandler<SearchVideoSegmentRequest, SearchVideoSegmentsResponse> {

	LambdaLogger logger;
	SegmentsDAO segDAO = new SegmentsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
	
	@Override
	public SearchVideoSegmentsResponse handleRequest(SearchVideoSegmentRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());
		
		ArrayList<Segment> segments = new ArrayList<Segment>();
		Boolean fail = false;
		String successResponse = "";
		String failMessage = "";
		
		
		
		if(req.getPhrase() == null && req.getCharacter() == null) {
			successResponse = "Nothing was inputed as a search";
		}
		
		else if (req.getPhrase() == null) {
			try {
				segments = segDAO.searchSegmentsCharacter(req.getCharacter(), req.getIsRemote());
				successResponse = "Successfully searched by character name";
				
			}
			catch(Exception e) {
				fail = true;	
				failMessage = "Failed to search by character";
			}
		}
		
		else if (req.getCharacter() == null) {
			try {
				segments = segDAO.searchSegmentsQoute(req.getPhrase(), req.getIsRemote());
			    successResponse = "Successfully searched by phrase";
			}
			catch(Exception e) {
				fail = true;
				failMessage = "Failed to search by phrase";
			}
		}
		else {
			try{
				segments = segDAO.searchSegmentsCharacterQoute(req.getPhrase(),req.getCharacter(), req.getIsRemote());
				successResponse = "Successfully searched by both phrase and character";
			}
			catch(Exception e) {
				fail = true;
				failMessage = "Failed to search by both phrase and character";
			}
		}
		
		SearchVideoSegmentsResponse response;
		if (fail) {
			response = new SearchVideoSegmentsResponse(400, failMessage);
		} else {
			response = new SearchVideoSegmentsResponse(segments,successResponse, 200);  // success
		}

		return response; 
	
	
	}

}
