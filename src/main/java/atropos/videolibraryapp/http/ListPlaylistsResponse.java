package atropos.videolibraryapp.http;

import java.util.ArrayList;

import atropos.videolibraryapp.model.Playlist;

public class ListPlaylistsResponse {
	public ArrayList<Playlist> playlist;
	public int statusCode;
	public String error;
	
	public ListPlaylistsResponse(ArrayList<Playlist> playlist, int code){
		this.playlist = playlist; 
		this.statusCode = code;
		this.error = "";
	}
	
	public ListPlaylistsResponse(int code, String errorMessage) {
		this.playlist = new ArrayList<Playlist>();
		this.error = errorMessage;
		this.statusCode = code;
	}
	
	public String toString() {
		if (playlist == null) {return "EmptyPlaylists";}
		else { return "ListPlaylists("+ playlist.size() + ")";}
	}
	
}
