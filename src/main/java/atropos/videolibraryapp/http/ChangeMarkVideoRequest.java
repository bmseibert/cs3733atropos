package atropos.videolibraryapp.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class ChangeMarkVideoRequest {

	String segment;
	Boolean isMarked;
	
	public ChangeMarkVideoRequest() {}
	
	public ChangeMarkVideoRequest(String segment, Boolean isMarked)
	{
		this.segment = segment;
		this.isMarked = isMarked;
	}
	
	public void setSegment(String newSegment) {this.segment = newSegment;}
	public String getSegment() {return segment;}
	
	public void setMarked(Boolean newStatus) {this.isMarked = newStatus;}
	public Boolean getMarked() {return isMarked;}
	
	
}
