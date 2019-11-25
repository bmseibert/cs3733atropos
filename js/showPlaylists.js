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
	        var playlistJson = playlists[i];	        
	        var name = playlistJson["name"];
	        
	        output = output + "<div id=\"playlist" + name + "\"><b>" + name + ":</b> </div>";
	        
	        
	        //<video id="num0" width="320" height="240" controls>
//	        <source src="" type="video/ogg">
//	        Your browser does not support the video tag.
//	        </video>
	      }
	    playlistList.innerHTML = output

	  } else {
	    var msg = "error";
	  }
}

function handleShowSegmentsClick(e){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", list_playlist_url, true);
	
	
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