// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "https://clu8n18nn1.execute-api.us-east-1.amazonaws.com/rho/"; 

var add_url    = base_url + "calculator";   // POST
var create_url = base_url + "segment";     // POST
var delete_url = base_url + "delete";       // Can't send JSON to DELETE request. This is POST
var list_url   = base_url + "segments";    // GET