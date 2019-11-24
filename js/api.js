// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var restApi = "y2b4rjh4x5";
var awsRegion = "us-east-1";
var base_url = "https://"+ restApi + ".execute-api." + AwsRegion +".amazonaws.com/"; 

var list_segment_url    = base_url + "segments";   // POST