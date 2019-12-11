package atropos.videolibraryapp;

import java.io.ByteArrayInputStream;
import java.net.URL;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import atropos.videolibraryapp.db.SegmentsDAO;
import atropos.videolibraryapp.http.UploadVideoSegmentRequest;
import atropos.videolibraryapp.http.UploadVideoSegmentResponse;
import atropos.videolibraryapp.model.Segment;

public class UploadVideoSegmentHandler implements RequestHandler<UploadVideoSegmentRequest, UploadVideoSegmentResponse>{
	
	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	
	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	public static final String BUCKET = "Star Trek Videos/";
	
	//helpers
	boolean IsDuplicate(String name) throws Exception {
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		Segment exists = dao.getSegment(name);
		if(exists == null) {
			return false;
		}else {
			return true;
		}
	}
	
	String uploadToBucket(String quote, String character, byte[] data) {
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(data.length);
		//omd.setContentEncoding("Base64");
		omd.setContentType("application/ogg");
		
		PutObjectResult res = s3.putObject(new PutObjectRequest("cs3733atropos", BUCKET + quote + ".ogg", bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		return s3.getUrl("cs3733atropos", BUCKET + quote + ".ogg").toExternalForm();
	}
	
	void uploadSegment(String name, String character, String url, boolean isRemote) throws Exception{
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		Segment seg = new Segment(name, character, url, false, isRemote);
		dao.addSegment(seg);
				
	}
	
	
	@Override
	public UploadVideoSegmentResponse handleRequest(UploadVideoSegmentRequest uvsr, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of UploadVideoSegment");
		logger.log(uvsr.toString());
		
		boolean isDup = false;
		boolean fail = false;
		String failMessage = "";
		String successResponse = "";
		
		//logic
		String segmentName = uvsr.name;
		String charName = uvsr.character;
		String url = uvsr.base64EncodedValue;
		boolean isRemote = uvsr.isRemote;
		byte[] encoded = java.util.Base64.getDecoder().decode(uvsr.base64EncodedValue);
	
		try {
			isDup = IsDuplicate(segmentName);
			try {
				if(!isDup) {
					try {	
						if(!isRemote) {
							url = uploadToBucket(segmentName, charName, encoded);
						}
					if(url != null) {
						uploadSegment(segmentName, charName, url, isRemote);
						successResponse = "Segment Created";
					}
					}catch(Exception e) {
						fail = true;
						failMessage = "Unable to Upload Video Segment to bucket";
					}
					
				}else {
					successResponse = "Already Exists";
				}
			}catch(Exception e) {
				fail = true;
				failMessage = "Unable to Upload Video Segment";
			}
		}catch (Exception e) {
			fail = true;
			failMessage = "Unable to Connect to Database";
		}
		
		UploadVideoSegmentResponse uvsresp;
		if (fail) {
			uvsresp = new UploadVideoSegmentResponse(400, failMessage); // fail
		} else {
			uvsresp = new UploadVideoSegmentResponse(successResponse, 200, url);  // success
		}
		return uvsresp; 
	}

}
