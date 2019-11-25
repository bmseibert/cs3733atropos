package atropos.videolibraryapp;



import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.PlaylistsDAO;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;
import atropos.videolibraryapp.http.EmptyRequest;
import atropos.videolibraryapp.http.ListPlaylistsResponse;
import atropos.videolibraryapp.model.Playlist;

public class ListPlaylistHandler implements RequestHandler< EmptyRequest,ListPlaylistsResponse>{
	
	LambdaLogger logger;
	
	//Helpers
	public ArrayList<Playlist> getPlaylists() throws Exception{
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		PlaylistsDAO dao = new PlaylistsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		ArrayList<Playlist> listPlaylist = new ArrayList<Playlist>();
		listPlaylist = dao.getAllPlaylists();
		return listPlaylist;
	}
	//Logic

	@Override
	public ListPlaylistsResponse handleRequest(EmptyRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
			
		boolean fail = false;
		
		String failMessage = "";
		ArrayList<Playlist> successResponse = new ArrayList<Playlist>();
		
		// Get List of Playlists
		try {
			successResponse = getPlaylists();
		} catch (Exception ex) {
			failMessage = "Could not grab list of playlists:: " + ex;
			fail = true;
		}
		
		
		ListPlaylistsResponse response;
		if (fail) {
			response = new ListPlaylistsResponse(400, failMessage);
		} else {
			response = new ListPlaylistsResponse(successResponse, 200);  // success
		}

		return response; 
	}
	
}

