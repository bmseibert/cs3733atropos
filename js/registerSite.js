function processCreateResponse(result) {
  console.log("result:" + result);
  var js = JSON.parse(result);
  var error = js["name"];
  console.log(error + "fully uploaded");
}

function processRegisterSiteResponse(result){
	var js = JSON.parse(result);
	var name = js["name"];
	
	alert(name + "fully added site");
}

function processGetRemoteSegmentsResponse(result){
	var js = JSON.parse(result);
	var error = js["error"];
	var status = js["statusCode"];
	console.log(error + "fully got remote segments");
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
	        uploadRemoteSegments(xhrGetSegs.responseText);
	      } else {
	    	processGetRemoteSegmentsResponse("N/A");
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


function uploadRemoteSegments(result){
	var js = JSON.parse(result);
	var segments = js["segments"];
	
	for (var i = 0; i < segments.length; i++) {
		var segment = segments[i];
		var data = {};
		data["character"] = segment["character"];
		data["name"] = segment["text"];
		data["base64EncodedValue"] = segment["url"];
		data["isRemote"] = true;
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhrUpload = new XMLHttpRequest();
		xhrUpload.open("POST", upload_segment_url, true);
		
		xhrUpload.send(js);

		  // This will process results and update HTML as appropriate. 
		xhrUpload.onload = function () {
		    console.log(xhrUpload);
		    console.log(xhrUpload.request);
		    if (xhrUpload.readyState == XMLHttpRequest.DONE) {
		    	 if (xhrUpload.status == 200) {
			      console.log ("xhrUpload:" + xhrUpload.responseText);
			      //processCreateResponse(xhrUpload.responseText);
		    	 } else {
		    		 console.log("actual:" + xhrUpload.responseText)
					  var js = JSON.parse(xhrUpload.responseText);
					  var err = js["response"];
					  alert (err);
		    	 }
		    } else {
		      processCreateResponse("N/A");
		    }
		  };
	}
}


function handleRegisterSite(e){
	var form = document.registerSite;
	var data= {};
	validate();
	data["site"] = form.url.value;
	
	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", register_site, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if(xhr.readyState == XMLHttpRequest.DONE){
	    	 if (xhr.status == 200) {
	   	      console.log ("XHR:" + xhr.responseText);
	   	      processRegisterSiteResponse(xhr.responseText);
	       	 } else {
	       		 console.log("actual:" + xhr.responseText)
	   			  var js = JSON.parse(xhr.responseText);
	   			  var err = js["response"];
	   			  alert(err);
	       	 }
	       } else {
	    	  processRegisterSiteResponse("N/A");
	       }
	    
	}
}