package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.ListSiteHandler;
import atropos.videolibraryapp.http.EmptyRequest;
import atropos.videolibraryapp.http.ListSitesResponse;

public class ListSiteHandlerTest extends LambdaTest {
	
	void testSuccessListInput(String incoming, String outgoing) throws IOException {
    	ListSiteHandler handler = new ListSiteHandler();
    	EmptyRequest req = new Gson().fromJson(incoming, EmptyRequest.class);
       
    	ListSitesResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.error);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailListInput(String incoming, String outgoing) throws IOException {
    	ListSiteHandler handler = new ListSiteHandler();
    	EmptyRequest req = new Gson().fromJson(incoming, EmptyRequest.class);

    	ListSitesResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    @Test
    public void testListSegments() {
    	
    	String input = "{}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessListInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
}
