function handleSuccessRemove(){
	alert("Last segment removed from playlist successfully");
}


function handleFailedRemove(){
	alert("Last segment failed to remove from playlist");
}

function handleRemoveFromPlaylistClick(e){
	var form = document.addSegmentForm;
	var data= {};
	data["playlist"] = form.playlistName.value;
	
	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", append_segment_url, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if(xhr.readyState == XMLHttpRequest.DONE){
	    	var js = JSON.parse(xhr.responseText);
	    	var statusCode = js["statusCode"];
	    	
		    if(statusCode == 200){
		    	handleSuccessRemove();
		    }else{
		    	handleFailedRemove();
		    }
	    }
	    
	}
}