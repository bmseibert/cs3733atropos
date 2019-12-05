/*
 * 
 */

function processListPlaylistsResponse(result){
	console.log("result:" + result);
	var js = JSON.parse(result);
	var playlists = js["playlist"];
	var status = js["statusCode"];
	var output = "";

	
	if (status == 200) {
	    // Update computation result
	    for (var i = 0; i < playlists.length; i++) {
	    	console.log(playlists.length);
	        var playlistJson = playlists[i];	        
	        var name = playlistJson["name"];
	        
	        output = output + "<div id=\"segment" + name + "\"><b>" + name + ":</b><br></div>";
	        
	      }
	    playlistList.innerHTML = output;

	  } else {
	    var msg = "error";
	  }
}

function handleShowPlaylistsClick(e){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", list_playlist_url, true);
	
	// send the collected data as JSON
	xhr.send(null);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	        console.log ("XHR:" + xhr.responseText);
	        processListPlaylistsResponse(xhr.responseText);
	      } else {
	    	processListPlaylistsResponse("N/A");
		      } 
		}
	}
