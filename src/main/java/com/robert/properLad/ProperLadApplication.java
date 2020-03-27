package com.robert.properLad;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.robert.properLad.s3Services.S3Services;

@SpringBootApplication
public class ProperLadApplication {
	@Autowired
	S3Services s3Services;
	  
//	@Value("${jsa.s3.uploadfile}")
//	private String uploadFilePath;
//	  
//	@Value("${jsa.s3.key}")
//	private String downloadKey;
	
	public static void main(String[] args) {
		SpringApplication.run(ProperLadApplication.class, args);
	}
//	@Override
//	  public void run(String... args) throws Exception {
//	    System.out.println("---------------- START UPLOAD FILE ----------------");
//	    s3Services.uploadFile("IMG_0690.jpg", uploadFilePath);
//	    System.out.println("---------------- START DOWNLOAD FILE ----------------");
//	    s3Services.downloadFile(downloadKey);
//	    System.out.println("DONE!");
//	}
}
