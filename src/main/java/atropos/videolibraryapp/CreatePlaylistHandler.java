package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.PlaylistsDAO;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;
import atropos.videolibraryapp.model.Playlist;


// Add the request and responses for these

public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResponse> {
	
	LambdaLogger logger;
	
	boolean CreatePlaylist(String name) throws Exception{
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		PlaylistsDAO dao = new PlaylistsDAO();
		
		Playlist exist = dao.getPlaylist(name);
		Playlist play = new Playlist(name);
		
		if (exist == null) {
			return dao.createNewPlaylist(play);
		} else {
			return false;
		}
	}
	
	@Override
	public CreatePlaylistResponse handleRequest(CreatePlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());
		
		boolean fail = false;
		boolean didWork = false;
		
		String playlistName = "";
		String failMessage = "";
		String successResponse = "";
		
		// Get the playlist name
		try {
			playlistName = req.getPlaylistName();
		} catch (Exception ex) {
			failMessage = "Playlist name does not exist";
			fail = true;
		}
		
		// playlist is created
		try {
			didWork = CreatePlaylist(playlistName);
		} catch(Exception e) {
			failMessage = "Playlist already exists";
			fail = true;
		}
		
		CreatePlaylistResponse response;
		if (fail) {
			response = new CreatePlaylistResponse(400, failMessage);
		} else {
			response = new CreatePlaylistResponse(successResponse, 200);  // success
		}

		return response; 
	}
	
}
