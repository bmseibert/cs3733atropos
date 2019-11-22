package atropos.http;

import java.util.ArrayList;

import model.Segment;

public class ListVideoSegmentsResponse {
	public ArrayList<Segment> segment;
	public int statusCode;
	public String error;
	
	public ListVideoSegmentsResponse(int code, ArrayList<Segment> segment) {
		this.error = "";
		this.statusCode = code;
		this.segment = segment;
	}
	
	public ListVideoSegmentsResponse(int code, String errorMessage) {
		this.statusCode = code;
		this.error = errorMessage;
		this.segment = new ArrayList<Segment>();
	}
	
	public String toString() {
		if (segment == null) {return "EmptySegment";}
		else { return "ListSegment("+ segment.size() + ")";}
	}

}
