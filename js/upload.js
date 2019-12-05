function processCreateResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);
  var js = JSON.parse(result);
  var error = js["error"];
  var status = js["statusCode"];
  alert(error);
  // refreshSegmentsList();
  //TODO: will need to update the segments list after upload
}

function handleCreateClick(e) {
  var form = document.uploadForm;
 
  var data = {};
  data["character"] = form.characterName.value;
  data["name"] = form.characterQuote.value;
  
  // base64EncodedValue":"data:text/plain;base64,My4xND....."
  var segments = document.uploadForm.base64EncodedValue.value.split(',');
  data["base64EncodedValue"] = segments[1];  // skip first one 

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", upload_segment_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processCreateResponse(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
}