package atropos.http;

public class CreatePlaylistResponse {
	public String name;
	public int statusCode;
	public String error;
	
	public CreatePlaylistResponse(String name, int code) {
		this.name = name;
		this.statusCode = code;
		this.error = "";
	}
	
	public CreatePlaylistResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.name = "";
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}
	
	

}
