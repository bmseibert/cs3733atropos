package atropos.videolibraryapp.http;

public class UploadVideoSegmentResponse {
	public int statusCode;
	public String error;
	public String name;
	public String url;
	
	public UploadVideoSegmentResponse(int code, String name) {
		this.error = "";
		this.name = name;
		this.statusCode = code;
		this.url = "";
	}
	
	public UploadVideoSegmentResponse(String errorMessage, int code, String url) {
		this.error = errorMessage;
		this.name = "";
		this.statusCode = code;
		this.url = url;
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}

}
