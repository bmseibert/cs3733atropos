package atropos.http;

public class DeleteVideoSegmentResponse {
	public String name;
	public int statusCode;
	public String error;
	
	public DeleteVideoSegmentResponse(String name, int code) {
		this.name = name;
		this.statusCode = code;
	}
	
	public DeleteVideoSegmentResponse(String name, int code, String errorMessage) {
		this.name = name;
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) { 
			return "DeleteResponse(" + name + ")";
		} else {
			return "ErrorResult(" + name + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
