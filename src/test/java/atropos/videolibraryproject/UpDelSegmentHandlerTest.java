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
	
//    void testFailDeleteInput(String incoming, String outgoing) throws IOException {
//    	DeleteSegmentHandler handler = new DeleteSegmentHandler();
//    	DeleteVideoSegmentRequest req = new Gson().fromJson(incoming, DeleteVideoSegmentRequest.class);
//
//    	DeleteVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
//    	
//        Assert.assertEquals(400, resp.statusCode);
//    }
    
	void testSuccessUploadInput(String incoming, String outgoing) throws IOException {
    	UploadVideoSegmentHandler handler = new UploadVideoSegmentHandler();
    	UploadVideoSegmentRequest req = new Gson().fromJson(incoming, UploadVideoSegmentRequest.class);
       
    	UploadVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	// Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
//    void testFailUploadInput(String incoming, String outgoing) throws IOException {
//    	UploadVideoSegmentHandler handler = new UploadVideoSegmentHandler();
//    	UploadVideoSegmentRequest req = new Gson().fromJson(incoming, UploadVideoSegmentRequest.class);
//
//    	UploadVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
//    	
//        Assert.assertEquals(400, resp.statusCode);
//    }
//    
    
    @Test
    public void testUploadSegment() {
    	
    	String input = "{\"name\": \"Where is Sebs\", \"character\": \"Ben\", \"base64EncodedValue\": \"NDIwNjk=\", \"isRemote\": \"true\"}";
    	String RESULT = "Success";
    	String input1 = "{\"segment\": \"Where is Sebs\",\"isRemote\": \"true\"}";
    	
    	try {
        	testSuccessUploadInput(input, RESULT);
        	//testSuccessDeleteInput(input1, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    }
    
//    @Test
//    public void testUploadSegmentFail() {
//    	
//    	String RESULT = "Success";
//    	String input1 = "{\"segment\": \"Where is Sebs car keys?\"}";
//    	
//    	try {
//        	testFailDeleteInput(input1, RESULT);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    	
//    }
    
	
}
