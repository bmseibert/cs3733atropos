// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point

var base_url = "https://y2b4rjh4x5.execute-api.us-east-1.amazonaws.com/Beta/"; 

var list_segment_url    = base_url + "segments";   // POST
var list_playlist_url  = base_url + "playlists"; // GET

var upload_segment_url = base_url + "segment"; // POST