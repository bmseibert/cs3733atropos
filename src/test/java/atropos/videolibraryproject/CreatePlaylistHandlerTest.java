package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;

import com.google.gson.Gson;

import atropos.videolibraryapp.CreatePlaylistHandler;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;

public class CreatePlaylistHandlerTest extends LambdaTest {
	
    void testSuccessInput(String incoming) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);
       
    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);

    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

}
