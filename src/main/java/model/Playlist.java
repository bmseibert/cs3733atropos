package model;
import java.util.ArrayList;
/*
 * @author Tidd
 */
public class Playlist {
	final String name;
	ArrayList<Segment> segments = new ArrayList<Segment>();
	
	public Playlist(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Segment> getSegments(){
		return this.segments;
	}
	
	public String getLastSegmentName() {
		return this.segments.get(this.segments.size() - 1).getName();
	}
	
	public void appendVideoSegment(Segment seg) {
		this.segments.add(seg);
	}
	
	public void removeVideoSegment() {
		this.segments.remove(this.segments.size() - 1);
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Playlist) {
			Playlist other = (Playlist) o;
			return this.name.equals(other.name);
		}
		
		return false;  // not a Playlist
	}

}
