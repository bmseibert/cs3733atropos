package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.RegisterSiteHandler;
import atropos.videolibraryapp.UnregisterSiteHandler;
import atropos.videolibraryapp.http.ChangeRegisterSiteRequest;
import atropos.videolibraryapp.http.RegisterSiteResponse;
import atropos.videolibraryapp.http.UnregisterSiteResponse;



public class SiteRegistrationHandlersTest extends LambdaTest{
	
	void testSuccessRegisterInput(String incoming, String outgoing) throws IOException {
    	RegisterSiteHandler handler = new RegisterSiteHandler();
    	ChangeRegisterSiteRequest req = new Gson().fromJson(incoming, ChangeRegisterSiteRequest.class);
       
    	RegisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.error);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailRegisterInput(String incoming, String outgoing) throws IOException {
    	RegisterSiteHandler handler = new RegisterSiteHandler();
    	ChangeRegisterSiteRequest req = new Gson().fromJson(incoming, ChangeRegisterSiteRequest.class);

    	RegisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    void testSuccessUnRegisterInput(String incoming, String outgoing) throws IOException {
    	UnregisterSiteHandler handler = new UnregisterSiteHandler();
    	ChangeRegisterSiteRequest req = new Gson().fromJson(incoming, ChangeRegisterSiteRequest.class);
       
    	UnregisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.error);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailUnRegisterInput(String incoming, String outgoing) throws IOException {
    	UnregisterSiteHandler handler = new UnregisterSiteHandler();
    	ChangeRegisterSiteRequest req = new Gson().fromJson(incoming, ChangeRegisterSiteRequest.class);

    	UnregisterSiteResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    @Test
    public void testRegisterSite() {
    	
    	String input = "{\"site\": \"www.canwefindsebs.com\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessRegisterInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void testUnRegisterSite() {
    	
    	String input = "{\"site\": \"www.canwefindsebs.com\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessRegisterInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
}
