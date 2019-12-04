package atropos.videolibraryapp;

import java.io.ByteArrayInputStream;

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
	
	boolean uploadToBucket(String character, String quote, byte[] data) {
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(data.length);
		
		PutObjectResult res = s3.putObject(new PutObjectRequest("cs3733atropos", BUCKET + quote, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		return true;
	}
	
	void uploadSegment(String name, String character, String url) throws Exception{
		if (logger != null) { 
			logger.log("in createPlaylist"); 
		}
		
		SegmentsDAO dao = new SegmentsDAO(System.getenv("DB_url"),System.getenv("DB_name"),System.getenv("DB_password"));
		Segment seg = new Segment(name, character, url);
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
		String segmentName = uvsr.getName();
		String charName = uvsr.getCharacter();
		String url = "https://cs3733atropos.s3.amazonaws.com/Star+Trek+Videos/" + segmentName + ".ogg";
		byte[] encoded = java.util.Base64.getDecoder().decode(uvsr.getValue());
		try {
			isDup = IsDuplicate(segmentName);
			try {
				if(!isDup) {
					boolean inBucket = uploadToBucket(charName, segmentName, encoded);
						if(inBucket) {
							uploadSegment(segmentName, charName, url);
							successResponse = "Segment Created";
						}
						else {
							fail = true;
							failMessage = "Unable to to put segment in s3 bucket";
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
