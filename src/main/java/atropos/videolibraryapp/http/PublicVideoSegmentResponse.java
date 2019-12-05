package atropos.videolibraryapp.http;

import java.util.ArrayList;
import java.util.List;

import atropos.videolibraryapp.model.Segments;

public class PublicVideoSegmentResponse {
	public final List<Segments> segments;
	public final int statusCode;
	public final String error;
	
	public PublicVideoSegmentResponse (List<Segments> list, int code) {
		this.segments = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public PublicVideoSegmentResponse (int code, String errorMessage) {
		this.segments = new ArrayList<Segments>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) { return "EmptySegments"; }
		return "Segments(" + segments.size() + ")";
	}
}
