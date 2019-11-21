package model;

public class Segment {
	public final String name;
	public final String character;
	public final String url;
	public Boolean isMarked;
	
	public Segment(String name, String character, String url, Boolean isMarked) {
		this.name = name;
		this.character = character;
		this.url = url;
		this.isMarked = isMarked;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCharacter() {
		return this.character;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public Boolean getIsMarked() {
		return this.isMarked;
	}
	
	public void setIsMarked(Boolean b) {
		this.isMarked = b;
	}
	
	public void changeMarkedStatus() {
		this.isMarked = !this.isMarked;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Segment) {
			Segment other = (Segment) o;
			return name.equals(other.name);
		}
		
		return false;  // not a Constant
	}
}
