/*
*
*/


function processSearchSegmentsResponse(result){
	console.log("result:" + result);
	var js = JSON.parse(result);
	var segments = js["segments"];
	var output = "";

	if(segments.length < 1){
		alert("No Results found");
	}else{
		for (var i = 0; i < segments.length; i++) {
		    var constantJson = segments[i];	        
		    var name = constantJson["name"];
		    var charname = constantJson["character"];
		    var url = constantJson["url"];
		    
		    output = output + "<div id=\"segment" + name + "\"><b>" + name + "-</b>  " + charname + "<br></div>";
		    
		    var iframe = document.createElement('iframe');
		    iframe.src = url;
		    iframe.width = '320';
		    iframe.height = '240';
		
		    var bottom = document.getElementById('searchResults');
		    bottom.appendChild(iframe);
		    console.log("Creating iFrames");
		    
		  }
		searchResults.innerHTML = output;
	}
	
}

function handleSearchSegments(){
	var form = document.searchSegmentsForm
	var data = {};
	data["character"] = form.character.value
	console.log(form.character.value)
	data["phrase"] = form.phrase.value
	if (form.remote.checked) {  // be sure to flag system constant requests...
	     data["isRemote"] = true;
	  }else{
		data["isRemote"] = false;
	}
	
	var js = JSON.stringify(data);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", search_url, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	        console.log ("XHR:" + xhr.responseText);
	        processSearchSegmentsResponse(xhr.responseText);
	      } 
		}

}

