package atropos.videolibraryapp.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class DeleteVideoSegmentRequest {

	String segment;
	boolean isRemote;
	
	public DeleteVideoSegmentRequest() {}
	
	public DeleteVideoSegmentRequest(String segment, boolean isRemote)
	{
		this.segment = segment;
		this.isRemote = isRemote;
	}
	
	public void setSegment(String newSegment) {this.segment = newSegment;}
	
	public String getSegment() {return segment;}
	
	public void setIsRemote(boolean newBool) {this.isRemote=newBool;}
	public boolean getIsRemtoe() {return this.isRemote;}
}
