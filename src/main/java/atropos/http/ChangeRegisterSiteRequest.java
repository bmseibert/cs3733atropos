package atropos.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class ChangeRegisterSiteRequest {

	String site;
	
	public ChangeRegisterSiteRequest() {}
	
	public ChangeRegisterSiteRequest(String site) 
	{
		this.site = site;
	}
	
	public void setSite(String newSite) {this.site = newSite;}
	public String getSite() {return site;}
	
}
