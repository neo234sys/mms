package com.sbmtech.mms.service.impl;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbmtech.mms.service.S3Service;

import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.mediastoredata.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;


@Service
public class S3ServiceImpl implements S3Service {

	private static final Logger logger = LogManager.getLogger(S3ServiceImpl.class);
	
    @Autowired
    private S3Client s3Client;
    
    @Autowired
    private S3Presigner s3Presigner;

    private final String bucketName = "mms-dev-bucket-19032025";

//    public String uploadFile(MultipartFile file) throws IOException {
//        String fileName = file.getOriginalFilename();
//        s3Client.putObject(PutObjectRequest.builder()
//                        .bucket(bucketName)
//                        .key(fileName)
//                        .build(),
//                software.amazon.awssdk.core.sync.RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
//        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
//    }

//    public List<String> listFiles() {
//        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
//                .bucket(bucketName)
//                .build();
//        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);
//        List<String> fileUrls = new ArrayList<>();
//        for (S3Object s3Object : listObjectsV2Response.contents()) {
//            fileUrls.add("https://" + bucketName + ".s3.amazonaws.com/" + s3Object.key());
//        }
//        return fileUrls;
//    }
    
    public String uploadFile(MultipartFile file) throws IOException {
    	Integer unitid=16;
    	System.out.println("org filenmae="+file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        return fileName;  // Return the generated file name
    }

	@Override
	public List<String> listFiles() {
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);

        return listObjectsV2Response.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }

	/*@Override
	public LinkedList uploadBase64Images(Integer unitId,List<String> base64Images) {
		LinkedList<String> fileUrls = new LinkedList<>();

		int i=1;
        for (String base64Image : base64Images) {
            try {
            	if(base64Image!=null) {
	                if (base64Image.contains(",")) {
	                    base64Image = base64Image.split(",")[1];
	                }
	                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
	                String fileName =  UUID.randomUUID()+"_"+unitId + ".jpg";
	                PutObjectRequest putRequest = PutObjectRequest.builder()
	                        .bucket(bucketName)
	                        .key(fileName)
	                        .contentType("image/jpeg")
	                        .build();
	
	                s3Client.putObject(putRequest, RequestBody.fromBytes(imageBytes));
	                fileUrls.add(fileName);
	                System.out.println("file upload success for ="+i);
	                i++;
            	}

            } catch (Exception e) {
            	System.out.println("file upload failed for ="+i);
                throw new RuntimeException("Failed to upload image", e);
            }
        }

        return fileUrls;
    }*/
	
	public Map<String,String> uploadBase64Images(Integer unitId,Map<String,String> mapBase64Files) {
		//LinkedList<String> fileUrls = new LinkedList<>();
		Map<String,String> mapFileNames=new HashMap<>();

		int i=1;
		for (Map.Entry<String, String> entry : mapBase64Files.entrySet()) {
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
			try {
				if(StringUtils.isNotBlank(entry.getValue())) {
					String base64Image=entry.getValue();
					if (base64Image.contains(",")) {
	                    base64Image = base64Image.split(",")[1];
					}
	                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
	             //   String contentType = new Tika().detect(attachment);
		            String fileName =  UUID.randomUUID()+"_"+unitId + ".jpg";
		            PutObjectRequest putRequest = PutObjectRequest.builder()
		            		.bucket(bucketName)
		                    .key(fileName)
		                    .contentType("image/jpeg")
		                    .build();
		            s3Client.putObject(putRequest, RequestBody.fromBytes(imageBytes));
	                mapFileNames.put(entry.getKey(), fileName);
				}
			}catch(Exception ex) {
				logger.error("Failed to upload image unitId=",unitId);
			}
		}
		
       
        return mapFileNames;
    }
//	 public Map<String, String> getFilesWithPresignedUrls(List<String> fileNames) {
//	        return fileNames.stream()
//	                .collect(Collectors.toMap(fileName -> fileName, this::generatePresignedUrl));
//	 }
	 
	 public String generatePresignedUrl(Integer unitId,String fileName) {
		 String fileUrl="";
		 try {
	        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
	                .bucket(bucketName)
	                .key(fileName)
	                .build();

	        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
	                .signatureDuration(Duration.ofHours(1)) // URL valid for 1 hour
	                .getObjectRequest(getObjectRequest)
	                .build();

	        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
	        fileUrl= presignedRequest.url().toString();
		 }catch(Exception ex) {
			 logger.error("Failed to upload image unitId={} filename={}",unitId,fileName);
		 }
	        return fileUrl;
	 }
	 
	 
	
}