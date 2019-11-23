package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.CreatePlaylistHandler;
import atropos.videolibraryapp.http.AppendSegmentResponse;
import atropos.videolibraryapp.http.AppendVideoSegmentRequest;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;

public class AddSegmentHandlerTest extends LambdaTest{

	void testSuccessInput(String incoming, String outgoing) throws IOException {
    	AddSegmentHandler handler = new AddSegmentHandler();
    	AppendVideoSegmentRequest req = new Gson().fromJson(incoming, AppendVideoSegmentRequest.class);
       
    	AppendSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	// Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, String outgoing) throws IOException {
    	AddSegmentHandler handler = new AddSegmentHandler();
    	AppendVideoSegmentRequest req = new Gson().fromJson(incoming, AppendVideoSegmentRequest.class);

    	AppendSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
	
}
