/**
 * Refresh segment list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] segments 
 */
function refreshSegmentsList() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", list_url, true);
   xhr.send();
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processListResponse(xhr.responseText);
    } else {
      processListResponse("N/A");
    }
  };
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processListResponse(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var segList = document.getElementById('segmentList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var segmentJson = js.list[i];
    console.log(segmentJson);
    
    var sname = segmentJson["name"];
    var sval = segmentJson["value"];
    var sysvar = segmentJson["system"];
    if (sysvar) {
    	output = output + "<div id=\"const" + sname + "\"><b>" + sname + ":</b> = " + sval + "<br></div>";
    } else {
    	output = output + "<div id=\"const" + sname + "\"><b>" + sname + ":</b> = " + sval + "(<a href='javaScript:requestDelete(\"" + sname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
    }
  }

  // Update computation result
  segList.innerHTML = output;
}
