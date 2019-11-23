package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.PlaylistsDAO;
import atropos.videolibraryapp.http.DeletePlaylistRequest;
import atropos.videolibraryapp.http.DeletePlaylistResponse;
import atropos.videolibraryapp.model.Playlist;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, DeletePlaylistResponse>{

	LambdaLogger logger;
	
	//helpers
	boolean isInDB(String name) throws Exception{
		if (logger != null) { 
			logger.log("in check for db name"); 
		}
		PlaylistsDAO dao = new PlaylistsDAO();
		Playlist exist = dao.getPlaylist(name);
		
		if(exist == null) {
			return false;
		}else {
			return true;
		}
	}
	
	void deleteFromDB(String name) throws Exception{
		if (logger != null) { 
			logger.log("in check for db name"); 
		}
		PlaylistsDAO dao = new PlaylistsDAO();
		Playlist play = new Playlist(name);
		dao.deletePlaylist(play);
		
	}
	
	@Override
	public DeletePlaylistResponse handleRequest(DeletePlaylistRequest dpr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(dpr.toString());
		
		Boolean fail = false;
		String failMessage = "";
		String successMessage = "";
		Boolean exists;
		
		//logic
		String playlistName = dpr.getPlaylistName();
		try {	
			exists = isInDB(playlistName);
			if(exists) {
				try {
					deleteFromDB(playlistName);
					successMessage = "Success";
				}catch(Exception e){
					fail = true;
					failMessage = "Could not delete Playlist";
				}
			}else {
				successMessage = "Does not exist";
			}
		} catch(Exception e){
			failMessage = "Cannot Connect to Database";
			fail = true;
		}
		
		DeletePlaylistResponse delplayresp;
		if (fail) {
			delplayresp = new DeletePlaylistResponse(400, failMessage); // fail
		} else {
			delplayresp = new DeletePlaylistResponse(successMessage, 200);  // success
		}
		
		return delplayresp;
	}
}
