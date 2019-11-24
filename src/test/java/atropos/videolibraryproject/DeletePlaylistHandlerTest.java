package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.CreatePlaylistHandler;
import atropos.videolibraryapp.DeletePlaylistHandler;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;
import atropos.videolibraryapp.http.DeletePlaylistRequest;
import atropos.videolibraryapp.http.DeletePlaylistResponse;

public class DeletePlaylistHandlerTest extends LambdaTest{

	void testSuccessInput(String incoming, String outgoing) throws IOException {
    	DeletePlaylistHandler handler = new DeletePlaylistHandler();
    	DeletePlaylistRequest req = new Gson().fromJson(incoming, DeletePlaylistRequest.class);
       
    	DeletePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, String outgoing) throws IOException {
    	DeletePlaylistHandler handler = new DeletePlaylistHandler();
    	DeletePlaylistRequest req = new Gson().fromJson(incoming, DeletePlaylistRequest.class);

    	DeletePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    @Test 
    public void testDeleteHandler() {
    	String CREATE_INPUT_STRING =  "{\"playlistName\": \"testDeletePlaylistSean\"}";
    	String RESULT = "Success";
    	
    	// Make sure the test playlist is created before deleting it
//    	CreatePlaylistHandler createHandler = new CreatePlaylistHandler();
//    	CreatePlaylistRequest req = new Gson().fromJson(CREATE_INPUT_STRING, CreatePlaylistRequest.class);
//    	CreatePlaylistResponse resp = createHandler.handleRequest(req, createContext("create"));
//    	Assert.assertEquals(resp.statusCode, 200);
    	
    	try {
        	testSuccessInput(CREATE_INPUT_STRING, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
}
