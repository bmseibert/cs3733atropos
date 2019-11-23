package atropos.videolibraryapp.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class UploadVideoSegmentRequest {
	
	String character;
	String name; // The name of the segment is the phrase the character is saying
	String url;
	
	public UploadVideoSegmentRequest() {}
	
	public UploadVideoSegmentRequest(String character, String name, String url) 
	{
		this.character = character;
		this.name = name;
		this.url = url;
	}
	
	public void setCharacter(String newCharacter) {this.character = newCharacter;}
	public String getCharacter() {return character;}
	
	public void setName(String newName) {this.name = newName;}
	public String getName() {return name;}
	
	public void setURL(String url) {this.url = url;}
	public String getURL() {return url;}
}
