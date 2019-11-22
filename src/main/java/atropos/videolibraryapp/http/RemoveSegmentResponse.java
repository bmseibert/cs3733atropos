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

}
