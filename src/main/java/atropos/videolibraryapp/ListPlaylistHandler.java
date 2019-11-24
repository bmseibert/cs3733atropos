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
	public ArrayList<Playlist> getPlaylists(){
		PlaylistsDAO playDAO = new PlaylistsDAO((String)System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_pasword"));
		ArrayList<Playlist> listPlaylist = new ArrayList();
		listPlaylist = playDAO.getAllPlaylists();
		return listPlaylist;
	}
	//Logic

	@Override
	public ListPlaylistsResponse handleRequest(EmptyRequest req, Context context) {
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
			if(didWork) {
				successResponse = "Success";
			}else {
				successResponse = "Playlist already exists";
			}
		} catch(Exception e) {
			failMessage = "Unable to Create Playlist";
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

	//Response
	
}
