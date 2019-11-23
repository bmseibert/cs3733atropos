package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.CreatePlaylistHandler;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;

public class CreatePlaylistHandlerTest extends LambdaTest {
	
    void testSuccessInput(String incoming, String outgoing) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);
       
    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, String outgoing) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);

    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
    @Test
    public void testCreatePlaylist() {
    	String SAMPLE_INPUT_STRING =  "{\"playlistName\": \"testPlaylist\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessInput(SAMPLE_INPUT_STRING, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }

}
