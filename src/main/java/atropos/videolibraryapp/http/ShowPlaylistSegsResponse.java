package atropos.videolibraryapp.http;

import java.util.ArrayList;

import atropos.videolibraryapp.model.Segment;

public class ShowPlaylistSegsResponse {
    public ArrayList<Segment> segs;
	public String successResponse;
	public int statusCode;
	public String error;
 
 public ShowPlaylistSegsResponse() {}
 public ShowPlaylistSegsResponse(ArrayList<Segment> segs, String success, int code) {
	 this.segs = segs;
	 this.successResponse = success;
	 this.error = "";
	 this.statusCode = code;
	 
 }
 public ShowPlaylistSegsResponse(int code, String error) {
	 this.error = error;
	 this.statusCode = code;
	 this.successResponse = "";
 }
 
}
