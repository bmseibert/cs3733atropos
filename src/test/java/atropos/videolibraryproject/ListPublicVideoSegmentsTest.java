package atropos.videolibraryproject;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.ListPublicVideoSegments;
import atropos.videolibraryapp.http.EmptyRequest;
import atropos.videolibraryapp.http.PublicVideoSegmentResponse;



public class ListPublicVideoSegmentsTest extends LambdaTest{
	
	void testSuccessInput(String incoming, String outgoing) throws IOException {
    	ListPublicVideoSegments handler = new ListPublicVideoSegments();
    	EmptyRequest req = new Gson().fromJson(incoming, EmptyRequest.class);
       
    	PublicVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.error);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, String outgoing) throws IOException {
    	ListPublicVideoSegments handler = new ListPublicVideoSegments();
    	EmptyRequest req = new Gson().fromJson(incoming, EmptyRequest.class);

    	PublicVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    @Test
    public void testSuccessInput() {
    	String input = "{}";
    	String RESULT = "";
    	
    	try {
        	testSuccessInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
}
