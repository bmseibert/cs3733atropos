function handleSuccess(){
	alert("Segment mark status has changed");
}

function handleFailure(){
	alert("Segment mark status failed to change");
}



function handleMarkClick(e){
	var form = document.markSegment;
	var data = {};
	data["segment"] = form.segment.value;
	data["isMarked"] = true;
	
	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", mark_segment_url, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if(xhr.readyState == XMLHttpRequest.DONE){
	    	var js = JSON.parse(xhr.responseText);
	    	var statusCode = js["statusCode"];
	    	
		    if(statusCode == 200){
		    	handleSuccess();
		    }else{
		    	handleFailure();
		    }
	    }
	

function handleUnmarkClick(e){
	var form = document.markSegment;
	var data = {};
	data["segment"] = form.segment.value;
	data["isMarked"] = false;
	
	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", unmark_segment_url, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if(xhr.readyState == XMLHttpRequest.DONE){
	    	var js = JSON.parse(xhr.responseText);
	    	var statusCode = js["statusCode"];
	    	
		    if(statusCode == 200){
		    	handleSuccess();
		    }else{
		    	handleFailure();
		    }
	    }	    
	    
}