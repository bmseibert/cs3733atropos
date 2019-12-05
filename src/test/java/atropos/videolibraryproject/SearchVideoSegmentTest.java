package atropos.videolibraryproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.SearchSegmentsHandler;
import atropos.videolibraryapp.SearchSegmentsHandler;
import atropos.videolibraryapp.http.SearchVideoSegmentRequest;
import atropos.videolibraryapp.http.SearchVideoSegmentsResponse;

public class SearchVideoSegmentTest extends LambdaTest {
	
	

	void testSuccessInput(String incoming, String outgoing) throws IOException {
    	SearchSegmentsHandler handler = new SearchSegmentsHandler();
    	SearchVideoSegmentRequest req = new Gson().fromJson(incoming, SearchVideoSegmentRequest.class);
    	SearchVideoSegmentsResponse resp = handler.handleRequest(req, createContext("create"));
    	
    	Assert.assertEquals(outgoing, resp.successResponse);
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, String outgoing) throws IOException {
    	SearchSegmentsHandler handler = new SearchSegmentsHandler();
    	SearchVideoSegmentRequest req = new Gson().fromJson(incoming, SearchVideoSegmentRequest.class);
    	SearchVideoSegmentsResponse resp = handler.handleRequest(req, createContext("create"));
    	
        Assert.assertEquals(400, resp.statusCode);
    }
    
//    @Test
//    public void testSearchByPhrase() {
//    	
//    	String input = "{\"character\": \"\", \"phrase\": \"Captain\", \"isRemote\" : \"false\"}";
//    	String RESULT = "Successfully searched by phrase";
//    	
//    	try {
//        	testSuccessInput(input, RESULT);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    }
    
    @Test
    public void testSearchByCharacter() {
    	
    	String input = "{\"character\": \"Spock\", \"phrase\": \"\", \"isRemote\" : \"false\"}";
    	String RESULT = "Successfully searched by character";
    	
    	try {
        	testSuccessInput(input, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
     
//    @Test
//    public void testSearchByPhraseCharacter() {
//    	
//     	String input = "{\"character\": \"McCoy\", \"phrase\": \"Capt\", \"isRemote\" : \"false\"}";
//        String RESULT = "Successfully searched by both phrase and character";
//
//    	
//    	try {
//        	testSuccessInput(input, RESULT);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    }
//
//    @Test
//    public void testSearchEmpty() {
//    	
//     	String input = "{\"character\": \"\", \"phrase\": \"\", \"isRemote\" : \"false\"}";
//        String RESULT = "Success, but no input for search";
//
//    	
//    	try {
//        	testSuccessInput(input, RESULT);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    }

}
