function processDeleteResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("deleted :" + result);
  var js = JSON.parse(result);
  var name = js["name"];
  alert(name);
  //refreshConstantsList();
}

function requestDelete(val) {
   if (confirm("Request to delete " + val)) {
     processDelete(val);
   }
}

function processDelete(val) {
  var data = {};
  data["segment"] = deleteSegmentForm.characterQuote.value;
	if (deleteSegmentForm.remote.checked) {  // be sure to flag system constant requests...
	     data["isRemote"] = true;
	  }else{
		data["isRemote"] = false;
	}

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", delete_segment_url, true);  // Can't be DELETE since then no data sent via JSON

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
	  console.log(xhr);
	  console.log(xhr.request);
	  if (xhr.readyState == XMLHttpRequest.DONE) {
		  if (xhr.status == 200) {
			  console.log ("XHR:" + xhr.responseText);
			  processDeleteResponse(xhr.responseText);
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["error"];
			  alert (err);
		  }
	  } else {
		  processDeleteResponse("N/A");
	  }
  };
}
