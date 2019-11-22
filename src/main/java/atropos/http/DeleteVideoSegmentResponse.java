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
		
	}
}
