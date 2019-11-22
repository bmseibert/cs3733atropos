package atropos.http;

public class DeletePlaylistResponse {
	public final String name;
	public final int statusCode;
	public final String error;
	
	public DeletePlaylistResponse(String name, int code) {
		this.name = name;
		this.statusCode = code;
		this.error = "";
	}
	
	public DeletePlaylistResponse(String name, int code, String errorMessage) {
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
