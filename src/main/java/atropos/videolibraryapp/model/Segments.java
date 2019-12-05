package atropos.videolibraryapp.model;

public class Segments {
	final String text;
	final String character;
    final String url;
	
	public Segments(String text, String character, String url) {
		this.text = text;
		this.character = character;
		this.url = url;
	}
	
	public Segments(String text) {
		this.text = text;
		this.character = null;
		this.url = null;
	}
	
	public String getText() {
		return this.text;
	}
	
	public String getCharacter() {
		return this.character;
	}
	
	public String getUrl() {
		return this.url;
	}
	
}
