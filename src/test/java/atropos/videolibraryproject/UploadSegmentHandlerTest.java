package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.UploadVideoSegmentHandler;
import atropos.videolibraryapp.CreatePlaylistHandler;

import atropos.videolibraryapp.http.UploadVideoSegmentRequest;
import atropos.videolibraryapp.http.UploadVideoSegmentResponse;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;

public class UploadSegmentHandlerTest extends LambdaTest{

	void testSuccessInput(String incoming, String outgoing) throws IOException {
    	UploadVideoSegmentHandler handler = new UploadVideoSegmentHandler();
    	UploadVideoSegmentRequest req = new Gson().fromJson(incoming, UploadVideoSegmentRequest.class);
       
    	UploadVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	// Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, String outgoing) throws IOException {
    	UploadVideoSegmentHandler handler = new UploadVideoSegmentHandler();
    	UploadVideoSegmentRequest req = new Gson().fromJson(incoming, UploadVideoSegmentRequest.class);

    	UploadVideoSegmentResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
	
}
