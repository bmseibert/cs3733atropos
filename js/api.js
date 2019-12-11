// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point

var base_url = "https://y2b4rjh4x5.execute-api.us-east-1.amazonaws.com/Beta/"; 

var create_playlist_url = base_url + "playlist"; // POST
	var append_segment_url = create_playlist_url + "/" + "appendSegment"; // POST
	var delete_playlist_url = create_playlist_url + "/" + "delete"; // POST
	var remove_segment_url = create_playlist_url + "/" + "removeSegment"; // POST
	var show_segments_url = create_playlist_url + "/showSegments" //POST

var list_playlist_url = base_url + "playlists"; // GET

var remote_url = base_url + "remote"; 
	var list_sites_url = remote_url + "/" + "listSites"; // GET
	var register_site = remote_url + "/" + "registerSite"; // POST
	var un_register_site = remote_url + "/" + "unregisterSite"; // POST

var upload_segment_url = base_url + "segment"; // POST
	var delete_segment_url = upload_segment_url + "/" + "delete"; // POST
	var mark_segment_url = upload_segment_url + "/" + "mark"; // POST
	var unmark_segment_url = upload_segment_url + "/" + "unmark"; // POST

var list_segment_url = base_url + "segments";   // POST
	var search_url = list_segment_url + "/" + "search"; // POST


