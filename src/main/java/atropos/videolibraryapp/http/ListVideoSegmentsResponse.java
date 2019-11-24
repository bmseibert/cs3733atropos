package atropos.videolibraryapp.http;

import java.util.ArrayList;

import atropos.videolibraryapp.model.Segment;

public class ListVideoSegmentsResponse {
	public ArrayList<Segment> segment;
	public int statusCode;
	public String responseMessage;
	
	public ListVideoSegmentsResponse(int code, ArrayList<Segment> segment, String resp) {
		this.responseMessage = resp;
		this.statusCode = code;
		this.segment = segment;
	}
	
	public ListVideoSegmentsResponse(int code, String errorMessage) {
		this.statusCode = code;
		this.responseMessage = errorMessage;
		this.segment = new ArrayList<Segment>();
	}
	
	public String toString() {
		if (segment == null) {return "EmptySegment";}
		else { return "ListSegment("+ segment.size() + ")";}
	}

}
