package atropos.videolibraryapp.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class CreatePlaylistRequest {

	String playlistName;
	
	public CreatePlaylistRequest() {}
	
	public CreatePlaylistRequest(String playlistName) 
	{
		this.playlistName = playlistName;
	}
	
	public void setPlaylistName(String newName) {this.playlistName = newName;}
	
	public String getPlaylistName() {return playlistName;}
	
}

