package atropos.videolibraryapp.http;

public class RemoveSegmentRequest {
	
	String playlist;
	
	public RemoveSegmentRequest() {}
	
	public RemoveSegmentRequest(String playlist) {
		this.playlist = playlist;
	}
	
	public void setPlaylist(String playlist) {
		this.playlist = playlist;
	}
	
	public String getPlaylist() {
		return this.playlist;
	}

}
