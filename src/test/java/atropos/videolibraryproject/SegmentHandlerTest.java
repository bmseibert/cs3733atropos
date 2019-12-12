package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.AppendSegmentHandler;
import atropos.videolibraryapp.RemoveSegmentHandler;
import atropos.videolibraryapp.http.AppendSegmentRequest;
import atropos.videolibraryapp.http.AppendSegmentResponse;
import atropos.videolibraryapp.http.RemoveSegmentRequest;
import atropos.videolibraryapp.http.RemoveSegmentResponse;



public class SegmentHandlerTest extends LambdaTest{
	
    void testSuccessAppendInput(String incoming, String outgoing) throws IOException {
    	AppendSegmentHandler handler = new AppendSegmentHandler();
    	AppendSegmentRequest req = new Gson().fromJson(incoming, AppendSegmentRequest.class);
       
    	AppendSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailAppendInput(String incoming, String outgoing) throws IOException {
    	AppendSegmentHandler handler = new AppendSegmentHandler();
    	AppendSegmentRequest req = new Gson().fromJson(incoming, AppendSegmentRequest.class);

    	AppendSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    void testSuccessRemoveInput(String incoming, String outgoing) throws IOException {
    	RemoveSegmentHandler handler = new RemoveSegmentHandler();
    	RemoveSegmentRequest req = new Gson().fromJson(incoming, RemoveSegmentRequest.class);
       
    	RemoveSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailRemoveInput(String incoming, String outgoing) throws IOException {
    	RemoveSegmentHandler handler = new RemoveSegmentHandler();
    	RemoveSegmentRequest req = new Gson().fromJson(incoming, RemoveSegmentRequest.class);

    	RemoveSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    
    @Test
    public void testAppendSegment() {
    	
    	String input = "{\"playlist\": \"DIO\", \"segment\": \"We move together\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessAppendInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
    
    @Test
    public void testFailAppendSegment() {
    	
    	String input = "{\"playlist\": \"new22\", \"segment\": \"We move together4\"}";
    	String RESULT = "Success";
    	
    	try {
        	testFailAppendInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
    
    @Test
    public void testRemoveSegment() {
    	
    	String input = "{\"playlist\": \"DIO\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessRemoveInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
    
    @Test
    public void testFailRemoveSegment() {
    	
    	String input = "{\"playlist\": \"new22\"}";
    	String RESULT = "Success";
    	
    	try {
        	testFailRemoveInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }

}
