package atropos.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class DeletePlaylistRequest {
	
	String playlist;
	
	public DeletePlaylistRequest() {}
	
	public DeletePlaylistRequest(String playlist)
	{
		this.playlist = playlist;
	}
	
	public void setPlaylist(String newPlaylist) {this.playlist = newPlaylist;}
	
	public String getPlaylist() {return playlist;}
}
