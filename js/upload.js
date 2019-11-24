function processCreateResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  refreshConstantsList();
}

function handleCreateClick(e) {
  var form = document.createForm;
 
  var data = {};
  data["name"]               = form.segmentName.value;
  
  if (form.system.checked) {  // be sure to flag system constant requests...
     data["system"] = true;
  }
  
  // base64EncodedValue":"data:text/plain;base64,My4xND....."
  var segments = document.createForm.base64Encoding.value.split(',');
  data["base64EncodedValue"] = segments[1];  // skip first one 

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", create_url, true);

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