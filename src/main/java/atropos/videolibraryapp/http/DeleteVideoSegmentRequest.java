package atropos.videolibraryapp.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class DeleteVideoSegmentRequest {

	String segment;
	
	public DeleteVideoSegmentRequest() {}
	
	public DeleteVideoSegmentRequest(String segment)
	{
		this.segment = segment;
	}
	
	public void setSegment(String newSegment) {this.segment = newSegment;}
	
	public String getSegment() {return segment;}
}
