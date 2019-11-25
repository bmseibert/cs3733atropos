/*
 * 
 */

function processListSegmentsResponse(result){
	console.log("result:" + result);
	var js = JSON.parse(result);
	var segments = js["segment"];
	var status = js["statusCode"];
	var output = "";
	var output2 = ""

	
	if (status == 200) {
	    // Update computation result
	    for (var i = 0; i < segments.length; i++) {
	        var constantJson = segments[i];	        
	        var name = constantJson["name"];
	        var charname = constantJson["character"];
	        var url = constantJson["url"];
	        
	        output = output + "Quote: " + name + "  Character Name: " + charname + "  URL: " + url + "\n";
	        output2 = output2 + "<div id=\"segment" + name + "\"><b>" + name + ":</b> = " + "<video id=\"name" + name + "width="320" height="240" controls><source src=\"" + url + type="video/ogg">" + "<br></div>";
	        
	        
	        //<video id="num0" width="320" height="240" controls>
//	        <source src="" type="video/ogg">
//	        Your browser does not support the video tag.
//	        </video>
	      }
	    segmentList.innerHTML = output2

	  } else {
	    var msg = "error";
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