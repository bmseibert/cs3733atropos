

function processRegisterSiteResponse(result){
	var js = JSON.parse(result);
	var name = js["name"];
	
	alert(name + "fully added site");
}



function handleRegisterSite(e){
	var form = document.registerSite;
	var data= {};
	data["site"] = form.url.value;
	
	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", register_site, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if(xhr.readyState == XMLHttpRequest.DONE){
	    	 if (xhr.status == 200) {
	   	      console.log ("XHR:" + xhr.responseText);
	   	      processRegisterSiteResponse(xhr.responseText);
	       	 } else {
	       		 console.log("actual:" + xhr.responseText)
	   			  var js = JSON.parse(xhr.responseText);
	   			  var err = js["response"];
	   			  alert(err);
	       	 }
	       } else {
	    	  processRegisterSiteResponse("N/A");
	       }
	    
	}
}