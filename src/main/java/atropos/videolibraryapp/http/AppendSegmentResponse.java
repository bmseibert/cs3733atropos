package atropos.videolibraryapp.http;

public class AppendSegmentResponse {
	public int statusCode;
	public String error;
	public String name;
	
	public AppendSegmentResponse(String name, int code) {
		this.error = "";
		this.name = name;
		this.statusCode = code;
	}
	
	public AppendSegmentResponse(int code, String errorMessage) {
		this.error = errorMessage;
		this.name = "";
		this.statusCode = code;
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}

}
