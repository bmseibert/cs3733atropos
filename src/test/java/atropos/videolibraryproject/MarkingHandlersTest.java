package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.MarkSegmentHandler;
import atropos.videolibraryapp.UnmarkSegmentHandler;
import atropos.videolibraryapp.http.ChangeMarkVideoRequest;
import atropos.videolibraryapp.http.MarkSegmentResponse;
import atropos.videolibraryapp.http.UnmarkSegmentResponse;

public class MarkingHandlersTest extends LambdaTest{
	
	void testSuccessMarkInput(String incoming, String outgoing) throws IOException {
    	MarkSegmentHandler handler = new MarkSegmentHandler();
    	ChangeMarkVideoRequest req = new Gson().fromJson(incoming, ChangeMarkVideoRequest.class);
       
    	MarkSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailMarkInput(String incoming, String outgoing) throws IOException {
    	MarkSegmentHandler handler = new MarkSegmentHandler();
    	ChangeMarkVideoRequest req = new Gson().fromJson(incoming, ChangeMarkVideoRequest.class);

    	MarkSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    void testSuccessUnmarkInput(String incoming, String outgoing) throws IOException {
    	UnmarkSegmentHandler handler = new UnmarkSegmentHandler();
    	ChangeMarkVideoRequest req = new Gson().fromJson(incoming, ChangeMarkVideoRequest.class);
       
    	UnmarkSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailUnmarkInput(String incoming, String outgoing) throws IOException {
    	UnmarkSegmentHandler handler = new UnmarkSegmentHandler();
    	ChangeMarkVideoRequest req = new Gson().fromJson(incoming, ChangeMarkVideoRequest.class);

    	UnmarkSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    @Test
    public void testMarkSegment() {
    	
    	String input = "{\"segment\": \"We move together\", \"isMarked\": \"true\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessMarkInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
    
    @Test
    public void testUnmarkSegment() {
    	
    	String input = "{\"segment\": \"We move together\", \"isMarked\": \"false\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessUnmarkInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
    
}
