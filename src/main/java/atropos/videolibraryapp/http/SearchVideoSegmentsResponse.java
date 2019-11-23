package atropos.videolibraryapp.http;

public class SearchVideoSegmentsResponse {
	public String name;
	public int statusCode;
	public String error;
	
	public SearchVideoSegmentsResponse(String name, int code) {
		this.error = "";
		this.name = name;
		this.statusCode = code;
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
