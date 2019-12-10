/*
*
*/
function handleSuccessDeletePlaylist(val){
	alert("Playlist with name: " + val + " deleted");
}

function handleFailDeletePlaylist(val){
	alert("Playlist with name: " + val + " was unable to be deleted");
}


function handleDeletePlaylistClick(e){
	var form = document.deletePlaylist;

	var data = {};
	var val = form.playlist.value;
	data["playlistName"] = form.playlist.value;
	data["isRemote"] = form.remote.value;
	
	
	if(val != ""){
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", delete_playlist_url, true);
		
		// send the collected data as JSON
		xhr.send(js);
		
		xhr.onloadend = function () {
			console.log(xhr);
		    console.log(xhr.request);
		    if(xhr.readyState == XMLHttpRequest.DONE){
		    	var js = JSON.parse(xhr.responseText);
		    	var statusCode = js["statusCode"];
		    	
			    if(statusCode == 200){
			    	handleSuccessDeletePlaylist(val);
			    }else{
			    	handleFailDeletePlaylist(val);
			    }
		    }
		    
			}
	}else{
		alert("Playlist name cannot be empty");
	}
	
}