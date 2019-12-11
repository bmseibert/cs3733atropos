package atropos.videolibraryapp.model;

public class Segment {
	final String name;
	final String character;
    final String url;
	boolean isMarked;
	final boolean isRemote;
	
	public Segment(String name, String character, String url, boolean isMarked, boolean site) {
		this.name = name;
		this.character = character;
		this.url = url;
		this.isMarked = isMarked;
		this.isRemote = site;
	}
	
	public Segment(String name, String character, String url) {
		this.name = name;
		this.character = character;
		this.isMarked = false;
		this.url = url;
		this.isRemote = false;
	}
	
	public Segment(String name) {
		this.name = name;
		this.character = null;
		this.url = null;
		this.isMarked = false;
		this.isRemote = false;
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
	
	public boolean getIsMarked() {
		return this.isMarked;
	}
	
	public boolean getIsRemote() {
		return this.isRemote;
	}
	
	public void setIsMarked(boolean b) {
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
