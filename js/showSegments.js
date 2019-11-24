/*
 * 
 */

function processListSegmentsResponse(result){
	console.log("result:" + result);
	var js = JSON.parse(result);
	var segments = js["segment"];
	var status = js["statusCode"];
	
	if (status == 200) {
	    // Update computation result
	    document.addForm.result.value = segments
	  } else {
	    var msg = js["error"];
	    document.addForm.result.value = "error:" + msg
	  }
}

function handleShowSegmentsClick(e){
	var data = {};
	data["isRemote"] = "false";
	
	var js = JSON.stringify(data);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", list_segment_url, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	        console.log ("XHR:" + xhr.responseText);
	        processAddResponse(xhr.responseText);
	      } else {
	        processAddResponse("N/A");
	      }
	}
}