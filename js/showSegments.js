/*
 * 
 */

function processListSegmentsResponse(result){
	console.log("result:" + result);
	var js = JSON.parse(result);
	var segments = js["segment"];
	var status = js["statusCode"];
	var output = "";
	
	console.log("processing list segments response");

	
	if (status == 200) {
	    // Update computation result
		removeOldVideos();
	    for (var i = 0; i < segments.length; i++) {
	        var constantJson = segments[i];	        
	        var name = constantJson["name"];
	        var charname = constantJson["character"];
	        var url = constantJson["url"];
	        
	        //output = output + "<div id=\"segment" + name + "\"><b>" + name + ":</b> = " + url + "<br></div>";
	        
	        var iframe = document.createElement('iframe');
	        iframe.src = url;
	        iframe.width = '320';
	        iframe.height = '240';

	        var bottom = document.getElementById('bottom');
	        bottom.appendChild(iframe);
	        console.log("Creating iFrames");
	        
	      }
	    //segmentList.innerHTML = output;

	  } else {
	    var msg = "error";
	  }
}

function removeOldVideos(){
	const myNode = document.getElementById("bottom");
	while (myNode.firstChild) {
		myNode.removeChild(myNode.firstChild);
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
	        processListSegmentsResponse(xhr.responseText);
	      } else {
	    	processListSegmentsResponse("N/A");
		      }
		}
	}