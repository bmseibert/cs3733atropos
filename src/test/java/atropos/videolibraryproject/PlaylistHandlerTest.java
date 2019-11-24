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

public class PlaylistHandlerTest extends LambdaTest {
	
    void testSuccessCreateInput(String incoming, String outgoing) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);
       
    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	// Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailCreateInput(String incoming, String outgoing) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);

    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
	void testSuccessDeleteInput(String incoming, String outgoing) throws IOException {
    	DeletePlaylistHandler handler = new DeletePlaylistHandler();
    	DeletePlaylistRequest req = new Gson().fromJson(incoming, DeletePlaylistRequest.class);
       
    	DeletePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	// Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailDeleteInput(String incoming, String outgoing) throws IOException {
    	DeletePlaylistHandler handler = new DeletePlaylistHandler();
    	DeletePlaylistRequest req = new Gson().fromJson(incoming, DeletePlaylistRequest.class);

    	DeletePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    @Test
    public void testCreateandDeletePlaylist() {
    	String SAMPLE_INPUT_STRING =  "{\"playlistName\": \"testPlaylist1\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessCreateInput(SAMPLE_INPUT_STRING, RESULT);
        	testSuccessDeleteInput(SAMPLE_INPUT_STRING, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void testCreateDuplicatePlaylist() {
    	String SAMPLE_INPUT_STRING =  "{\"playlistName\": \"testPlaylist1\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessCreateInput(SAMPLE_INPUT_STRING, RESULT);
        	testFailCreateInput(SAMPLE_INPUT_STRING, RESULT);
        	testSuccessDeleteInput(SAMPLE_INPUT_STRING, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }

}
