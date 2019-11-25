package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.AppendSegmentHandler;
import atropos.videolibraryapp.CreatePlaylistHandler;
import atropos.videolibraryapp.DeletePlaylistHandler;
import atropos.videolibraryapp.http.AppendSegmentResponse;
import atropos.videolibraryapp.http.AppendSegmentRequest;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;
import atropos.videolibraryapp.http.DeletePlaylistRequest;
import atropos.videolibraryapp.http.DeletePlaylistResponse;


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
    
    

}
