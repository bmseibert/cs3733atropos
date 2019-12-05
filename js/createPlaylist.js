/*
 * 
 */
function handleSuccessPlaylistCreate(){
	alert("Playlist Uploaded Successfully");
}

function handleFailedPlaylistCreate(){
	alert("Playlist Failed to Upload");
}

function handleCreatePlaylistClick(e){
	var form = document.createPlaylist
	
	var data = {};
	data["playlistName"] = form.playlist.value;
	
	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", create_playlist_url, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if(xhr.readyState == XMLHttpRequest.DONE){
	    	var js = JSON.parse(xhr.responseText);
	    	var statusCode = js["statusCode"];
	    	
		    if(statusCode == 200){
		    	handleSuccessPlaylistCreate();
		    }else{
		    	handleFailedPlaylistCreate();
		    }
	    }
	    
		}

}