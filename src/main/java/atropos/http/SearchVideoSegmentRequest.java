package atropos.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class SearchVideoSegmentRequest {

	String character;
	String phrase;
	Boolean isRemote;
	
	public SearchVideoSegmentRequest() {}
	
	public SearchVideoSegmentRequest(String character, String phrase, Boolean isRemote)
	{
		this.character = character;
		this.phrase = phrase;
		this.isRemote = isRemote;
	}
	
	public void setCharacter(String newCharacter) {this.character = newCharacter;}
	public String getCharacter() {return character;}
	
	public void setPhrase(String newPhrase) {this.phrase = newPhrase;}
	public String getPhrase() {return phrase;}
	
	public void setIsRemote(Boolean newStatus) {this.isRemote = newStatus;}
	public Boolean getIsRemote() {return isRemote;}
}
