package model;
import java.util.ArrayList;

public class Playlist {
	public final String name;
	
	public Playlist(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void appendVideoSegment(Segment seg) {
		ArrayList<Segment> segments = new ArrayList<Segment>();
		segments.add(seg);
	}
	
	public void removeVideoSegment(Segment seg) {
		ArrayList<Segment> segments = new ArrayList<Segment>();
		segments.remove(seg);
	}

}
