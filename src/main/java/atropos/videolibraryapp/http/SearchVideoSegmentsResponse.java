package atropos.videolibraryapp.http;

import java.util.ArrayList;

import atropos.videolibraryapp.model.Segment;

public class SearchVideoSegmentsResponse {
	public String name;
	public int statusCode;
	public String error;
	public ArrayList<Segment> segments;
	
	
	public SearchVideoSegmentsResponse(ArrayList<Segment> segments, String name, int code) {
		this.error = "";
		this.name = name;
		this.statusCode = code;
		this.segments = segments;
	}
	
	public SearchVideoSegmentsResponse(int code, String errorMessage) {
		this.name = "";
		this.error = errorMessage;
		this.statusCode = code;
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}

}
