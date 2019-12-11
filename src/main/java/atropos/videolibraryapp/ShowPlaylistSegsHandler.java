package atropos.videolibraryapp;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.PlaylistsDAO;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.ShowPlaylistSegsResponse;
import atropos.videolibraryapp.model.Segment;

public class ShowPlaylistSegsHandler implements RequestHandler<CreatePlaylistRequest, ShowPlaylistSegsResponse> {

	LambdaLogger logger;
	PlaylistsDAO playDAO = new PlaylistsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
	
	@Override
	public ShowPlaylistSegsResponse handleRequest(CreatePlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());
		
		ArrayList<Segment> segments = new ArrayList<Segment>();
		Boolean fail = false;
		String successResponse = "";
		String failMessage = "";
	
		
		
		try {
			segments = playDAO.getPlaylist(req.getPlaylistName()).getSegments();
			successResponse = "Success";
			
		}
		catch(Exception e) {
			fail = true;	
			failMessage = "Failed";
		}
	
		ShowPlaylistSegsResponse response;
		if (fail) {
			response = new ShowPlaylistSegsResponse(400, failMessage);
		} else {
			response = new ShowPlaylistSegsResponse(segments,successResponse, 200);  // success
		}

		return response; 
	
	
	}

}
