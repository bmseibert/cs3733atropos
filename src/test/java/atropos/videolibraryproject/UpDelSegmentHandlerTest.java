package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.DeleteSegmentHandler;
import atropos.videolibraryapp.UploadVideoSegmentHandler;
import atropos.videolibraryapp.http.DeleteVideoSegmentRequest;
import atropos.videolibraryapp.http.DeleteVideoSegmentResponse;
import atropos.videolibraryapp.http.UploadVideoSegmentRequest;
import atropos.videolibraryapp.http.UploadVideoSegmentResponse;


public class UpDelSegmentHandlerTest extends LambdaTest {
	
	void testSuccessDeleteInput(String incoming, String outgoing) throws IOException {
    	DeleteSegmentHandler handler = new DeleteSegmentHandler();
    	DeleteVideoSegmentRequest req = new Gson().fromJson(incoming, DeleteVideoSegmentRequest.class);
       
    	DeleteVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailDeleteInput(String incoming, String outgoing) throws IOException {
    	DeleteSegmentHandler handler = new DeleteSegmentHandler();
    	DeleteVideoSegmentRequest req = new Gson().fromJson(incoming, DeleteVideoSegmentRequest.class);

    	DeleteVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
	void testSuccessUploadInput(String incoming, String outgoing) throws IOException {
    	UploadVideoSegmentHandler handler = new UploadVideoSegmentHandler();
    	UploadVideoSegmentRequest req = new Gson().fromJson(incoming, UploadVideoSegmentRequest.class);
       
    	UploadVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	// Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailUploadInput(String incoming, String outgoing) throws IOException {
    	UploadVideoSegmentHandler handler = new UploadVideoSegmentHandler();
    	UploadVideoSegmentRequest req = new Gson().fromJson(incoming, UploadVideoSegmentRequest.class);

    	UploadVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    
    
    
    @Test
    public void testUploadSegment() {
    	
    	String input = "{\"name\": \"Where is Sebs\", \"character\": \"Ben\", \"url\": \"www.whereissebs.com\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessUploadInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
    
    @Test
    public void testDeleteSegment() {
    	
    	String input = "{\"segment\": \"Where is Sebs\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessDeleteInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
	
}
