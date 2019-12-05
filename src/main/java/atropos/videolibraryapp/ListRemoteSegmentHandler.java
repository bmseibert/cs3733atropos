package atropos.videolibraryapp;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import atropos.videolibraryapp.model.Segment;


public class ListRemoteSegmentHandler {
	public LambdaLogger logger;

	@Override
	public RemoteSegmentsResponse handleRequestimplements RequestHandler<Object,RemoteSegmentsResponse> (Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all remote segments");

		Segment one = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output1.ogg", "worker", "one to beam up");
		Segment two = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output2.ogg", "bones", "he's dead, Jim.");
		Segment three = new Segment("https://cs3733wpi.s3.amazonaws.com/segments/output3.ogg", "worker", "Kirk, Out.");
		
		List<Segment> list = new ArrayList<Segment>();
		list.add(one);
		list.add(two);
		list.add(three);
		
		RemoteSegmentsResponse response = new RemoteSegmentsResponse(list, 200);
		return response;
	}
}
