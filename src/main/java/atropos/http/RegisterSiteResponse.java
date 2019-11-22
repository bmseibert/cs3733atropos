package atropos.http;

public class RegisterSiteResponse {
	public String name;
	public int statusCode;
	public String error;
	
	public RegisterSiteResponse(String name, int code) {
		this.name = name;
		this.statusCode = code;
		this.error = "";
	}
	
	public RegisterSiteResponse(int code, String errorMessage) {
		this.error = errorMessage;
		this.name = "";
		this.statusCode = code;
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}

}
