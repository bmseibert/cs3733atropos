


function handleShowPlaylistSegs(e, playlist){ 
	
	alert(playlist);
	
	
	
	var data = {};
	data["playlistName"] = form.playlist.value;
	
	if(playName != ""){
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
	}else{
		alert("Playlist name cannot be empty");
	}

	
	
	
	
	
	/*for (var i = 0; i < segments.length; i++) {	        
        var name = 
        var charname = 
        var url = 
        
        output = output + "<div id=\"segment" + name + "\"><b>" + name + "-</b>  " + charname + "<br></div>";
        
        var iframe = document.createElement('iframe');
        iframe.src = url;
        iframe.width = '320';
        iframe.height = '240';

        var bottom = document.getElementById('bottom');
        bottom.appendChild(iframe);
        console.log("Creating iFrames");
*/
}