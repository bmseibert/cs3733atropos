package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.RegisterSiteHandler;
import atropos.videolibraryapp.ShowPlaylistSegsHandler;
import atropos.videolibraryapp.UnregisterSiteHandler;
import atropos.videolibraryapp.http.ChangeRegisterSiteRequest;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.RegisterSiteResponse;
import atropos.videolibraryapp.http.ShowPlaylistSegsResponse;
import atropos.videolibraryapp.http.UnregisterSiteResponse;

public class ShowPlaylistSegsHandlerTest extends LambdaTest {
	
	void testSuccessPlaylistSegsInput(String incoming, String outgoing) throws IOException {
    	ShowPlaylistSegsHandler handler = new ShowPlaylistSegsHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);
       
    	ShowPlaylistSegsResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.successResponse);
        Assert.assertEquals(200, resp.statusCode);
    }
	  
    @Test
    public void testGetPlaylistSegs() {
    	
    	String input = "{\"playlistName\": \"DIO\"}";
    	String RESULT = "Success";
    	
    	try {
        	testSuccessPlaylistSegsInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    }
