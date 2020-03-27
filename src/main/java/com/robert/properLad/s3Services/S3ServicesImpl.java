package com.robert.properLad.s3Services;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class S3ServicesImpl implements S3Services {
	private Logger logger = LoggerFactory.getLogger(S3ServicesImpl.class);
	
	@Autowired
	private AmazonS3 s3client;
	
	@Value("${jsa.s3.bucket}")
	private String bucketName;
	
	@Override
	public void downloadFile(String keyName) {
		
		try {
			
			System.out.println("Downloading an object");
			S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, keyName));
			System.out.println("Content-Type: "  + s3object.getObjectMetadata().getContentType());
			Utility.displayText(s3object.getObjectContent());
			logger.info("================= Import File - Done! ====================");
			
		} catch (AmazonServiceException ase) {
			logger.info("Caught an AmazonServiceException from GET requests, rejected reasons: ");
			logger.info("Error message: " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS error Code: " + ase.getErrorCode());
			logger.info("Error type: " + ase.getErrorType());
			logger.info("Request ID: " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			logger.info("caught an AmazonClientException: ");
			logger.info("Error message: " + ace.getMessage());
		} catch (IOException ioe) {
			logger.info("IOE Error Message: " + ioe.getMessage());
		}
	}
	
	@Override
	public void uploadFile(String keyName, String uploadFilePath) {
		try {
			File file = new File(uploadFilePath);
			s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
			logger.info("--------Upload File - Done!----------");
		} catch (AmazonServiceException ase) {
			logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons: ");
			logger.info("Error message:    " + ase.getMessage());
			logger.info("HTTP Status Code:    " + ase.getStatusCode());
			logger.info("AWS Error code:    " + ase.getErrorCode());
			logger.info("Error type:    " + ase.getErrorType());
			logger.info("Request ID:    " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			logger.info("Caught an AmazonClientException: ");
			logger.info("Error message: " + ace.getMessage());
		}
	}
}
