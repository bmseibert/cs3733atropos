package atropos.videolibraryapp.http;

public class UnmarkSegmentResponse {
	public int statusCode;
	public String error;
	public String name;
	
	public UnmarkSegmentResponse (int code, String name) {
		this.name = name;
		this.statusCode = code;
		this.error = "";
	}
	
	public UnmarkSegmentResponse(String errorMessage, int code) {
		this.error = errorMessage;
		this.name = "";
		this.statusCode = code;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) { 
			return "UnmarkResponse(" + name + ")";
		} else {
			return "ErrorResult(" + name + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
	
}
