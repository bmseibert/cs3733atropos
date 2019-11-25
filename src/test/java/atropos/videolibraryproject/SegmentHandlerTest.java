package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.AppendSegmentHandler;
import atropos.videolibraryapp.http.AppendSegmentRequest;
import atropos.videolibraryapp.http.AppendSegmentResponse;



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
    
    @Test
    public void testAppendSegment() {
    	
    	String input = "{\"playlist\": \"new\", \"segment\": \"We move together\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessAppendInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
    

}
