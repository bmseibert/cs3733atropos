

function alertSuccess(){
	alert("Successfully unregistered site");
}


function alertFailure(){
	alert("Failed to unregister site")
}


function handleUnregisterSiteClick(e){
	var form = document.registerSite;
	var data = {};
	data["site"] = form.url.value;

	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", un_register_site, true);
	
	// send the collected data as JSON
	xhr.send(js);
	
	xhr.onloadend = function () {
		console.log(xhr);
	    console.log(xhr.request);
	    if(xhr.readyState == XMLHttpRequest.DONE){
	    	var js = JSON.parse(xhr.responseText);
	    	var statusCode = js["statusCode"];
	    	
		    if(statusCode == 200){
		    	alertSuccess();
		    }else{
		    	alertFailure();
		    }
	    }
	    
		}
}