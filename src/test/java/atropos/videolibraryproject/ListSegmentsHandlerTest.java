package atropos.videolibraryproject;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.ListSegmentsHandler;
import atropos.videolibraryapp.http.ListVideoSegmentsRequest;
import atropos.videolibraryapp.http.ListVideoSegmentsResponse;
import atropos.videolibraryapp.model.Segment;


public class ListSegmentsHandlerTest extends LambdaTest{
	
	void testSuccessInput(String incoming, String outgoing) throws IOException {
    	ListSegmentsHandler handler = new ListSegmentsHandler();
    	ListVideoSegmentsRequest req = new Gson().fromJson(incoming, ListVideoSegmentsRequest.class);
       
    	ListVideoSegmentsResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.responseMessage);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, String outgoing) throws IOException {
    	ListSegmentsHandler handler = new ListSegmentsHandler();
    	ListVideoSegmentsRequest req = new Gson().fromJson(incoming, ListVideoSegmentsRequest.class);

    	ListVideoSegmentsResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    @Test
    public void testListSegments() {
    	
    	String input = "{\"isRemote\": \"false\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void testRemoteListSegments() {
    	String input = "{\"isRemote\": \"true\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
}
