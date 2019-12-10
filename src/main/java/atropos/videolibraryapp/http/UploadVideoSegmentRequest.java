package atropos.videolibraryapp.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class UploadVideoSegmentRequest {
	
	public String character;
	public String name; // The name of the segment is the phrase the character is saying
	public String base64EncodedValue;
	public boolean isRemote;
	
	public UploadVideoSegmentRequest() {}
	
	public UploadVideoSegmentRequest(String character, String name, String base64EncodedValue, boolean isRemote) 
	{
		this.character = character;
		this.name = name;
		this.base64EncodedValue = base64EncodedValue;
		this.isRemote = isRemote;
	}
	
	
	public void setCharacter(String newCharacter) {this.character = newCharacter;}
	public String getCharacter() {return character;}
	
	public void setName(String newName) {this.name = newName;}
	public String getName() {return name;}
	
	public void setValue(String newValue) {this.base64EncodedValue = newValue;}
	public String getValue() {return base64EncodedValue;}
	
	public void setIsRemote(boolean newBool) {this.isRemote = newBool;}
	public boolean getIsRemote() {return this.isRemote;}
	
}
