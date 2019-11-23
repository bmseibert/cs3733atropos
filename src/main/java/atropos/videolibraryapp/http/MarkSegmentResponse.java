package atropos.videolibraryapp.http;

public class MarkSegmentResponse {
	public String name;
	public int statusCode;
	public String error;
	
	public MarkSegmentResponse(String name, int code) {
		this.name = name;
		this.statusCode = code;
		this.error = "";
	}
	
	public MarkSegmentResponse(int code, String errorMessage) {
		this.error = errorMessage;
		this.statusCode = code;
		this.name = "";
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}

}
