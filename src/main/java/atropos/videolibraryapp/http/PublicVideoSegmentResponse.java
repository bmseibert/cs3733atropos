package atropos.videolibraryapp.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.heineman.calculator.model.Segment;

public class PublicVideoSegmentResponse {
	public final List<Segment> segments;
	public final int statusCode;
	public final String error;
	
	public PublicVideoSegmentResponse (List<Segment> list, int code) {
		this.segments = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public PublicVideoSegmentResponse (int code, String errorMessage) {
		this.segments = new ArrayList<Segment>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) { return "EmptySegments"; }
		return "Segments(" + segments.size() + ")";
	}
}
