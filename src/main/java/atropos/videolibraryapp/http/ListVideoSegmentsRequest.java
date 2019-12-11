package atropos.videolibraryapp.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class ListVideoSegmentsRequest {

	Boolean isRemote;
	
	public ListVideoSegmentsRequest() {}
	
	public ListVideoSegmentsRequest(Boolean isRemote) 
	{
		this.isRemote = isRemote;
	}
	
	public void setIsRemote(Boolean newStatus) {this.isRemote = newStatus;}
	public boolean getIsRemote() {return isRemote;}
	
}
