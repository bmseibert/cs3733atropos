package atropos.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class UploadVideoSegmentRequest {
	
	String character;
	String name; // The name of the segment is the phrase the character is saying
	
	public UploadVideoSegmentRequest() {}
	
	public UploadVideoSegmentRequest(String character, String name) 
	{
		this.character = character;
		this.name = name;
	}
	
	public void setCharacter(String newCharacter) {this.character = newCharacter;}
	public String getCharacter() {return character;}
	
	public void setName(String newName) {this.name = newName;}
	public String getName() {return name;}
}
