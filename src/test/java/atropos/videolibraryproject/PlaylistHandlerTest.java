package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.CreatePlaylistHandler;
import atropos.videolibraryapp.DeletePlaylistHandler;
import atropos.videolibraryapp.ListPlaylistHandler;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;
import atropos.videolibraryapp.http.DeletePlaylistRequest;
import atropos.videolibraryapp.http.DeletePlaylistResponse;
import atropos.videolibraryapp.http.EmptyRequest;
import atropos.videolibraryapp.http.ListPlaylistsResponse;

public class PlaylistHandlerTest extends LambdaTest {
	
    void testSuccessCreateInput(String incoming, String outgoing) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);
       
    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
    
    void testSuccessCreateDupilcateInput(String incoming, String outgoing) {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);
       
    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.name);
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
    	
    	Assert.assertEquals(outgoing, resp.name);
        Assert.assertEquals(200, resp.statusCode);
    }
	
//    void testFailDeleteInput(String incoming, String outgoing) throws IOException {
//    	DeletePlaylistHandler handler = new DeletePlaylistHandler();
//    	DeletePlaylistRequest req = new Gson().fromJson(incoming, DeletePlaylistRequest.class);
//
//    	DeletePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
//    	
//        Assert.assertEquals(400, resp.statusCode);
//    }
    
    void testSuccessListPlaylistInput(String incoming, String outgoing) {
    	ListPlaylistHandler handler = new ListPlaylistHandler();
    	EmptyRequest req = new Gson().fromJson(incoming, EmptyRequest.class);
       
    	ListPlaylistsResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(200, resp.statusCode);
    }
    
    @Test
    public void testCreateandDeletePlaylist() {
    	String SAMPLE_INPUT_STRING =  "{\"playlistName\": \"testPlaylist14\"}";
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
    	String SAMPLE_INPUT_STRING =  "{\"playlistName\": \"testPlaylist13\"}";
    	String RESULT = "Success";
    	String RESULT2 = "Playlist already exists";
    	
    	try {
        	testSuccessCreateInput(SAMPLE_INPUT_STRING, RESULT);
        	testSuccessCreateDupilcateInput(SAMPLE_INPUT_STRING, RESULT2);
        	testSuccessDeleteInput(SAMPLE_INPUT_STRING, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void testFailPlaylist() {
    	String SAMPLE_INPUT_STRING =  "{\"playlistNamez\": \"testPlaylist54\"}";
    	String SAMPLE_INPUT_STRING2 =  "{\"playlistNamez\": \"testPlaylist54\"}";

    	String RESULT = "Success";
    	
    	try {
        	testFailCreateInput(SAMPLE_INPUT_STRING2, RESULT);
        	//testFailDeleteInput(SAMPLE_INPUT_STRING, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void testListPlaylists() {
    	String SAMPLE_INPUT_STRING =  "{\"playlistName\": \"testPlaylist1\"}";
    	String RESULT = "Success";
    	
    	testSuccessListPlaylistInput(SAMPLE_INPUT_STRING, RESULT);
    }
    


}
