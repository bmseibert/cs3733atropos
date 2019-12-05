/*
 * 
 */
function refreshRemoteSiteList{
	
	var data = {}
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	  xhr.open("GET", list_sites_url, true);
	// send the collected data as JSON
	  xhr.send(js);
	   
	  console.log("sent");

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	      console.log ("XHR:" + xhr.responseText);
	      processRemoteListResponse(xhr.responseText);
	    } else {
	    	processRemoteListResponse("N/A");
	    }
	  };
	
}