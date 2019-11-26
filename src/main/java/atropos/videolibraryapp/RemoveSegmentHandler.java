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
	
	Playlist getPlaylist(String name) throws Exception{
		if (logger != null) { 
			logger.log("in getPlaylist"); 
		}
		PlaylistsDAO dao = new PlaylistsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		return dao.getPlaylist(name);
	}
	
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
		
		try {
			play = getPlaylist(playlistName);
		}catch(Exception e) {
			fail = true;
			failMessage = "Unable to connect to database";
		}
		//logic
		try {
			RemoveSegment(play);
			successMessage = "Success";
		}catch (Exception e) {
			fail = true;
			failMessage = "Unable to connect to database";
		}
		
		RemoveSegmentResponse rsresp;
		if(fail) {
			rsresp = new RemoveSegmentResponse(400, failMessage);
		}else {
			rsresp = new RemoveSegmentResponse(successMessage, 200);
		}
		
		return rsresp;
	}
}
