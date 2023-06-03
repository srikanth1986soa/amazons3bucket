package Amazons3Bucket.amazons3bucket;
import java.io.IOException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class UploadObject {

	public static void main(String[] args) throws IOException {
		Regions clientRegion = Regions.US_EAST_1;
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();
			ListObjectsRequest lor = new ListObjectsRequest().withBucketName("ssettivari1").withPrefix("test/");
			ObjectListing objectListing = s3Client.listObjects(lor);
			for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
				if (summary.getKey().contains("folder$")) {
					s3Client.deleteObject("ssettivari1", summary.getKey());
					System.out.println("Object is deleted from s3:"+  summary.getKey());
				}
				
			}

		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
}