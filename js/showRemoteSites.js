/*
 * 
 */
function processShowRemoteSitesResponse(result){
	console.log("result:" + result);
	var js = JSON.parse(result);
	var sites = js["site"];
	var status = js["statusCode"];
	var output = "";

	
	if (status == 200) {
	    // Update computation result
	    for (var i = 0; i < sites.length; i++) {
	    	console.log(sites.length);
	        var siteJson = sites[i];	        
	        var name = siteJson["website"];
	        
	        output = output + "<div id=\"segment" + name + "\"> - " + name + "<br></div>";
	        
	      }
	    siteList.innerHTML = output;

	  } else {
	    var msg = "error";
	  }
}


function handleShowRemoteSites(e){
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", list_sites_url, true);
	
	// send the collected data as JSON
	xhr.send();
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	        console.log ("XHR:" + xhr.responseText);
	        processShowRemoteSitesResponse(xhr.responseText);
	      } else {
	    	  processShowRemoteSitesResponse("N/A");
		      } 
		}
	}
