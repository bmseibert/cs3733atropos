package atropos.videolibraryapp.http;

import java.util.ArrayList;

import atropos.videolibraryapp.model.Site;



public class ListSitesResponse {
	public ArrayList<Site> site;
	public int statusCode;
	public String error;
	
	public ListSitesResponse(ArrayList<Site> site, int code){
		this.site = site;
		this.statusCode = code;
		this.error = "";
	}
	
	public ListSitesResponse(int code, String errorMessage) {
		this.error = errorMessage;
		this.statusCode = code;
		this.site = new ArrayList<Site>();
	}
	
	public String toString() {
		if (site == null) {return "EmptyWebsites";}
		else { return "ListWebsite("+ site.size() + ")";}
	}

}
