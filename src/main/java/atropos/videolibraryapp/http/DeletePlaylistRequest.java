package atropos.videolibraryapp.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class DeletePlaylistRequest {
	
	String playlistName;
	
	public DeletePlaylistRequest() {}
	
	public DeletePlaylistRequest(String playlist)
	{
		this.playlistName = playlist;
	}
	
	public void setPlaylistName(String newPlaylist) {this.playlistName = newPlaylist;}
	
	public String getPlaylistName() {return playlistName;}
}
