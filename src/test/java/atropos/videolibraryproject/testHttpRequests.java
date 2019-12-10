package atropos.videolibraryproject;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import atropos.videolibraryapp.http.AppendSegmentRequest;
import atropos.videolibraryapp.http.AppendSegmentResponse;
import atropos.videolibraryapp.http.ChangeMarkVideoRequest;
import atropos.videolibraryapp.http.ChangeRegisterSiteRequest;
import atropos.videolibraryapp.http.CreatePlaylistRequest;
import atropos.videolibraryapp.http.CreatePlaylistResponse;
import atropos.videolibraryapp.http.DeletePlaylistRequest;
import atropos.videolibraryapp.http.DeletePlaylistResponse;
import atropos.videolibraryapp.http.DeleteVideoSegmentRequest;
import atropos.videolibraryapp.http.DeleteVideoSegmentResponse;
import atropos.videolibraryapp.http.ListPlaylistsResponse;
import atropos.videolibraryapp.http.ListSitesResponse;
import atropos.videolibraryapp.http.ListVideoSegmentsRequest;
import atropos.videolibraryapp.http.ListVideoSegmentsResponse;
import atropos.videolibraryapp.http.MarkSegmentResponse;
import atropos.videolibraryapp.http.PublicVideoSegmentResponse;
import atropos.videolibraryapp.http.RegisterSiteResponse;
import atropos.videolibraryapp.http.RemoveSegmentRequest;
import atropos.videolibraryapp.http.RemoveSegmentResponse;
import atropos.videolibraryapp.http.SearchVideoSegmentRequest;
import atropos.videolibraryapp.http.SearchVideoSegmentsResponse;
import atropos.videolibraryapp.http.UnmarkSegmentResponse;
import atropos.videolibraryapp.http.UnregisterSiteResponse;
import atropos.videolibraryapp.http.UploadVideoSegmentRequest;
import atropos.videolibraryapp.http.UploadVideoSegmentResponse;

public class testHttpRequests extends LambdaTest{
	
	@Test
	public void testAppendSegmentRequest() {
		String playlist = "playlist";
		String segment = "segment";
		
		AppendSegmentRequest asr = new AppendSegmentRequest(playlist, segment);
		asr.setPlaylist(playlist);
		asr.setSegment(segment);
	}
	
	@Test
	public void testAppendSegmentResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		
		AppendSegmentResponse asr = new AppendSegmentResponse(playlist, code);
		asr.toString();
	}
	
	@Test
	public void testChangeMarkRequest() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		ChangeMarkVideoRequest cmr = new ChangeMarkVideoRequest(segment, pos);
		cmr.setSegment(segment);
		cmr.setMarked(true);
		cmr.getMarked();
	}
	
	@Test
	public void testChangeRegisterSiteRequest() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		ChangeRegisterSiteRequest crsr = new ChangeRegisterSiteRequest(segment);
		crsr.setSite(segment);
	}
	
	@Test
	public void testCreatePlaylistRequest() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		CreatePlaylistRequest cpr = new CreatePlaylistRequest(playlist);
		cpr.setPlaylistName(playlist);
	}
	
	@Test
	public void testCreatePlaylistResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		CreatePlaylistResponse cpr = new CreatePlaylistResponse(playlist, code);
		cpr.toString();
	}
	
	@Test
	public void testDeletePlaylistRequest() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		DeletePlaylistRequest dpr = new DeletePlaylistRequest(playlist);
		dpr.setPlaylistName(playlist);
		
	}
	
	@Test
	public void testDeletePlaylistResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		DeletePlaylistResponse dpr = new DeletePlaylistResponse(code, playlist);
		dpr.toString();
		
		DeletePlaylistResponse dpr2 = new DeletePlaylistResponse(400, playlist);
		dpr2.toString();
		
	}
	
	@Test 
	public void testDeleteVideoSegmentRequest() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		DeleteVideoSegmentRequest dvsr = new DeleteVideoSegmentRequest(segment, false);
		dvsr.setSegment(segment);
	}
	
	@Test
	public void testDeleteVideoSegmentResponse() {
		
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		DeleteVideoSegmentResponse dvsr = new DeleteVideoSegmentResponse(code, playlist);
		dvsr.toString();
		DeleteVideoSegmentResponse dvsr2 = new DeleteVideoSegmentResponse(400, playlist);
		dvsr2.toString();
	}
	
	@Test
	public void testListPlaylistsResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		ListPlaylistsResponse lpr = new ListPlaylistsResponse(code, playlist);
		lpr.toString();
	}
	
	@Test
	public void testListSitesResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;
		
		ListSitesResponse lsr = new ListSitesResponse(code, playlist);
		lsr.toString();
	}
	
	@Test
	public void testListVideoSegmentsRequest() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		ListVideoSegmentsRequest lvsr = new ListVideoSegmentsRequest(pos);
		lvsr.setIsRemote(pos);
	}
	
	@Test
	public void testListVideoSegmentsResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		ListVideoSegmentsResponse lvsr = new ListVideoSegmentsResponse(code, playlist);
		lvsr.toString();
	}
	
	@Test
	public void testMarkSegmentResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		MarkSegmentResponse msr = new MarkSegmentResponse(code, playlist);
		msr.toString();
	}
	
	@Test
	public void testPublicVideoSegmentResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		PublicVideoSegmentResponse pvsr = new PublicVideoSegmentResponse(code, playlist);
		pvsr.toString();
	}
	
	@Test
	public void testRegisterSiteResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		RegisterSiteResponse rsr = new RegisterSiteResponse(code, playlist);
		rsr.toString();
	}
	
	@Test
	public void testRemoveSegmentRequest() {
		
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		RemoveSegmentRequest rsr = new RemoveSegmentRequest(playlist);
		rsr.setPlaylist(playlist);
	}
	
	@Test
	public void testRemoveSegmentResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		RemoveSegmentResponse rsr = new RemoveSegmentResponse(code, playlist);
		rsr.toString();
		RemoveSegmentResponse rsr2 = new RemoveSegmentResponse(400, playlist);
		rsr2.toString();

	}
	
	@Test
	public void testSearchVideoSegmentRequest() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		SearchVideoSegmentRequest svsr = new SearchVideoSegmentRequest(playlist, playlist, pos);
		svsr.setCharacter(segment);
		svsr.setIsRemote(pos);
		svsr.setPhrase(segment);
	}
	
	@Test
	public void testSearchVideoSegmentsResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		SearchVideoSegmentsResponse svsr = new SearchVideoSegmentsResponse(code, playlist);
		svsr.toString();
	}
	
	@Test
	public void testUnmarkSegmentResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		UnmarkSegmentResponse usr = new UnmarkSegmentResponse(code, playlist);
		usr.toString();
		UnmarkSegmentResponse usr2 = new UnmarkSegmentResponse(400, playlist);
		usr2.toString();
		
	}
	
	@Test
	public void testUnregisterSiteResponse() {
		
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		UnregisterSiteResponse usr = new UnregisterSiteResponse(code, playlist);
		usr.toString();
		UnregisterSiteResponse usr2 = new UnregisterSiteResponse(400, playlist);
		usr2.toString();
	}
	
	@Test
	public void testUploadVideoSegmentRequest() {
		
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		UploadVideoSegmentRequest uvsr = new UploadVideoSegmentRequest(playlist, playlist, playlist, false);
		uvsr.getCharacter();
		uvsr.setCharacter(segment);
		uvsr.getName();
		uvsr.setName(segment);
		uvsr.getValue();
		uvsr.setValue(segment);
	}
	
	@Test
	public void testUploadVideoSegmentResponse() {
		String playlist = "playlist";
		String segment = "segment";
		int code = 200;
		Boolean pos = true;	
		
		UploadVideoSegmentResponse uvsr = new UploadVideoSegmentResponse(code, playlist);
		uvsr.toString();
	}
}
