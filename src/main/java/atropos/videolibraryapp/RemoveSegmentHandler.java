package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.PlaylistsDAO;
import atropos.videolibraryapp.http.RemoveSegmentRequest;
import atropos.videolibraryapp.http.RemoveSegmentResponse;
import atropos.videolibraryapp.model.Playlist;

public class RemoveSegmentHandler implements RequestHandler<RemoveSegmentRequest, RemoveSegmentResponse>{
	
	LambdaLogger logger;
	
	//helpers
	void RemoveSegment(Playlist playlist) throws Exception{
		if (logger != null) { 
			logger.log("in RemoveSegment"); 
		}
		PlaylistsDAO dao = new PlaylistsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		dao.deleteSegmentFromPlaylist(playlist);
	}
	
	@Override
	public RemoveSegmentResponse handleRequest(RemoveSegmentRequest rsr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(rsr.toString());
		
		Boolean fail = false;
		String failMessage = "";
		String successMessage = "";
		String playlistName = rsr.getPlaylist();
		Playlist play = new Playlist(playlistName);
		
		//logic
		try {
			RemoveSegment(play);
			successMessage = "Success";
		}catch (Exception e) {
			fail = true;
			failMessage = "";
		}
		
		RemoveSegmentResponse rsresp;
		if(fail) {
			rsresp = new RemoveSegmentResponse(failMessage, 400);
		}else {
			rsresp = new RemoveSegmentResponse(200, successMessage);
		}
		
		return rsresp;
	}
}
