package atropos.videolibraryapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.db.PlaylistsDAO;
import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.AppendSegmentRequest;
import atropos.videolibraryapp.http.AppendSegmentResponse;
import atropos.videolibraryapp.model.Playlist;
import atropos.videolibraryapp.model.Segment;

public class AppendSegmentHandler implements RequestHandler<AppendSegmentRequest, AppendSegmentResponse>{
	
	LambdaLogger logger;
	
	//helpers
	void AppendSegment(Playlist playlist) throws Exception{
		if (logger != null) { 
			logger.log("in appendSegment"); 
		}
		PlaylistsDAO dao = new PlaylistsDAO((String)System.getenv("DB_url"),(String)System.getenv("DB_name"),(String)System.getenv("DB_password"));
		dao.addSegmentToPlaylist(playlist);
	}
	
	Segment getSegment(String name) throws Exception{
		if (logger != null) { 
			logger.log("in getSegment"); 
		}
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		return dao.getSegment(name);
	}
	
	@Override
	public AppendSegmentResponse handleRequest(AppendSegmentRequest asr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(asr.toString());
		
		boolean fail = false;
		String failMessage = "";
		String successMessage = "";
		String playlistName = asr.getPlaylist();
		String segmentName = asr.getSegment();
		Segment segment;
		Playlist play = new Playlist(playlistName);
		
		//logic
		try {
			segment = getSegment(segmentName);
		}catch(Exception e) {
			fail = true;
			failMessage = "Unable to connect to Segment database";
		}
		
		try {
			AppendSegment(play);
		}catch(Exception e) {
			fail = true;
			failMessage = "Unable to connect to Playlist database";
		}
		
		AppendSegmentResponse asresp;
		if(fail) {
			asresp = new AppendSegmentResponse(400, failMessage);
		}else {
			asresp = new AppendSegmentResponse(successMessage, 200);
		}
		return asresp;
	}
}
