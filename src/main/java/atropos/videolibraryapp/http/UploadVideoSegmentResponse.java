package atropos.videolibraryapp.http;

public class UploadVideoSegmentResponse {
	public int statusCode;
	public String error;
	public String name;
	
	public UploadVideoSegmentResponse(int code, String name) {
		this.error = "";
		this.name = name;
		this.statusCode = code;
	}
	
	public UploadVideoSegmentResponse(String errorMessage, int code) {
		this.error = errorMessage;
		this.name = "";
		this.statusCode = code;
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}

}
