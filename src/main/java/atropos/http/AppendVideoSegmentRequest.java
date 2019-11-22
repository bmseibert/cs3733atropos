package atropos.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class AppendVideoSegmentRequest {
	String playlist;
	String segment;
	
	public AppendVideoSegmentRequest() {}
	
	public AppendVideoSegmentRequest(String playlist, String segment) 
	{
		this.playlist = playlist;
		this.segment = segment;
	}
	
	public void setPlaylist(String newPlaylist) {this.playlist = newPlaylist;}
	public String getPlaylist() {return playlist;}
	
	public void setSegment(String newSegment) {this.segment = newSegment;}
	public String getSegment() {return segment;}
	
	public String toString() {
		return "Playlist and Segment(" + playlist + "," + segment + ")";
	}
}
