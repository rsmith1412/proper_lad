package com.robert.properLad.s3Services;

import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class UploadFile {
	private final String AWSKEY = System.getenv("jsa.aws.access_key_id");
	private final String AWSPASS = System.getenv("jsa.aws.secret_access_key");
	
	public String getAWSKEY() {
		return AWSKEY;
	}
	public String getAWSPASS() {
		return AWSPASS;
	}
	
	public String Upload(String bucketName, String keyName, InputStream filePath, ObjectMetadata metaData) {
		String awsKey = this.getAWSKEY();
		String awsPass = this.getAWSPASS();
		System.out.println(awsKey);
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsKey, awsPass);
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-west-1").build();
		try {
			s3.putObject(bucketName, keyName, filePath, metaData);
			return s3.getUrl("topladimages", keyName).toString();
		} catch (AmazonServiceException e) {
			System.out.println("Hello");
			System.err.println(e.getErrorMessage());
			System.exit(1);
			return null;
		}
	}
}
