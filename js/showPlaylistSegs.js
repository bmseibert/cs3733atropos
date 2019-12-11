


function handleShowPlaylistSegs(e, playlist){ 
	
	var data = {};
	data["playlistName"] = playlist;
	
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", show_segments_url, true);
		
		// send the collected data as JSON
		xhr.send(js);
		
		xhr.onloadend = function () {
			console.log(xhr);
		    console.log(xhr.request);
		    if(xhr.readyState == XMLHttpRequest.DONE){
		    	var js = JSON.parse(xhr.responseText);
		    	var statusCode = js["statusCode"];
		    	processResponse(xhr.responseText);
			    if(statusCode == 200){
			    	//handleSuccessPlaylistCreate();
			    	//alert(playlist);
			    }else{
			    	//handleFailedPlaylistCreate();
			    }
		    }
		
		    else{
		    	alert("Playlist name cannot be empty");
		    }	
	}
}

function removeOldSegments(){
	const myNode = document.getElementById("playsegs");
	while (myNode.firstChild) {
		myNode.removeChild(myNode.firstChild);
	}
}

function processResponse(result){
	console.log("result:" + result);
	var js = JSON.parse(result);
	var segments = js["segs"];
	var status = js["statusCode"];
	var output = "";

	removeOldSegments();
	for (var i = 0; i < segments.length; i++) {	        
		var segment = segments[i];
		var name = segment["name"];
		var charname = segment["character"];
		var url = segment["url"];
    
		output = output + "<div id=\"segment" + name + "\"><b>" + name + "-</b>  " + charname + "<br></div>";
    
		var iframe = document.createElement('iframe');
		iframe.src = url;
		iframe.width = '320';
		iframe.height = '240';

		var bottom = document.getElementById('playsegs');
		bottom.appendChild(iframe);
		console.log("Creating iFrames");

	}
}