package atropos.videolibraryapp.http;

public class UnregisterSiteResponse {
	public int statusCode;
	public String name;
	public String error;
	
	public UnregisterSiteResponse(int code, String name) {
		this.error = "";
		this.name = name;
		this.statusCode = code;
	}
	
	public UnregisterSiteResponse(String errorMessage, int code) {
		this.error = errorMessage;
		this.name = "";
		this.statusCode = code;
	}
	public String toString() {
		if (statusCode / 100 == 2) { 
			return "UnregisterResponse(" + name + ")";
		} else {
			return "ErrorResult(" + name + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}

}
