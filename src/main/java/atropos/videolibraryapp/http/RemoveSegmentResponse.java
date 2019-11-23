package atropos.videolibraryapp.http;

public class RemoveSegmentResponse {
	public String name;
	public String error;
	public int statusCode;
	
	public RemoveSegmentResponse(String name, int code) {
		this.error = "";
		this.name = name;
		this.statusCode = code;
	}
	
	public RemoveSegmentResponse(int code, String errorMessage) {
		this.error = errorMessage;
		this.name = "";
		this.statusCode = code;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) { 
			return "RemoveResponse(" + name + ")";
		} else {
			return "ErrorResult(" + name + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}

}
