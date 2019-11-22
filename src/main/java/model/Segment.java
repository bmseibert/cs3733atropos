package model;

public class Segment {
	final String name;
	final String character;
    final String url;
	Boolean isMarked;
	final String site;
	public Segment(String name, String character, String url, Boolean isMarked, String site) {
		this.name = name;
		this.character = character;
		this.url = url;
		this.isMarked = isMarked;
		this.site = site;
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
	
	public String getSite() {
		return this.getSite();
	}
	
	public void setIsMarked(Boolean b) {
		this.isMarked = b;
	}
	
	public void toggleIsMarked() {
		this.isMarked = !this.isMarked;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Segment) {
			Segment other = (Segment) o;
			return this.name.equals(other.name);
		}
		
		return false;  // not a Segment
	}
}
