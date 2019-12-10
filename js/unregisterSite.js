

function alertSuccess(){
	alert("Successfully unregistered site");
}


function alertFailure(){
	alert("Failed to unregister site")
}

function removeRemoteSegments(result){
	var js = JSON.parse(result);
	var segments = js["segments"];
	
	for (var i = 0; i < segments.length; i++) {
		var segment = segments[i];
		data["name"] = segment["text"];
		data["isRemote"] = true;
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhrDelete = new XMLHttpRequest();
		
		xhrDelete.open("POST", delete_segment_url, true);  // Can't be DELETE since then no data sent via JSON

		  // send the collected data as JSON
		xhrDelete.send(js);

		  // This will process results and update HTML as appropriate. 
		xhrDelete.onloadend = function () {
			  console.log(xhr);
			  console.log(xhr.request);
			  if (xhrDelete.readyState == XMLHttpRequest.DONE) {
				  if (xhrDelete.status == 200) {
					  console.log ("xhrDelete:" + xhrDelete.responseText);
					  //processDeleteResponse(xhrDelete.responseText);
				  } else {
					  console.log("actual:" + xhrDelete.responseText)
					  var js = JSON.parse(xhrDelete.responseText);
					  var err = js["error"];
					  alert (err);
				  }
			  } else {
				  //processDeleteResponse("N/A");
			  }
		  }
	}
}


function getSegmentsFromRemoteSite(url, apikey){
	
	var xhrGetSegs = new XMLHttpRequest();
	xhrGetSegs.open("GET", url, true);
	xhrGetSegs.setRequestHeader("x-api-key", apikey);

	xhrGetSegs.send();
	      
	console.log("sent");
	
	xhrGetSegs.onload = function () {
		console.log(xhrGetSegs);
	    console.log(xhrGetSegs.request);
	    if (xhrGetSegs.readyState == XMLHttpRequest.DONE) {
	        console.log ("xhrGetSegs:" + xhrGetSegs.responseText);
	        removeRemoteSegments(xhrGetSegs.responseText);
	      } else {
	    	//processGetRemoteSegmentsResponse("N/A");
		      } 
		}
}

function validate() {
	  var form = document.registerSite;
	  var urlapi = form.url.value;

	  var q = urlapi.indexOf("?apikey=");
	  if (q == -1) {
	    alert("Your input must be of the form 'url?apikey=...'");
	  } else {
	    var url = urlapi.substring(0, q);
	    var api = urlapi.substring(q+8);
	    console.log(url);
	    console.log(api);

	    getSegmentsFromRemoteSite(url, api);
	  }
	}

function handleUnregisterSiteClick(e){
	var form = document.registerSite;
	var data = {};
	validate()
	data["site"] = form.url.value;

	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", un_register_site, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if(xhr.readyState == XMLHttpRequest.DONE){
	    	var js = JSON.parse(xhr.responseText);
	    	var statusCode = js["statusCode"];
	    	
		    if(statusCode == 200){
		    	alertSuccess();
		    }else{
		    	alertFailure();
		    }
	    }
	    
		}
}