package com.sbmtech.mms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class S3DeletionService {
	
	
	@Value("${mms.app.s3Bucket}")
	private String BUCKET_NAME;


    private final S3Client s3Client;

   // private static final String BUCKET_NAME = "mms-dev-bucket-19032025";
   // mms-local-bucket-29042025
    

    public S3DeletionService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void deleteFiles(List<String> fileNames) {
        for (String fileName : fileNames) {
            if (fileName != null && !fileName.trim().isEmpty()) {
                DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(fileName)
                        .build();
                s3Client.deleteObject(deleteObjectRequest);
            }
        }
    }
    
    public void deleteFilesInBatch(List<String> fileNames) {
        List<ObjectIdentifier> objectIdentifiers = new ArrayList<>();

        for (String fileName : fileNames) {
            if (fileName != null && !fileName.trim().isEmpty()) {
                objectIdentifiers.add(ObjectIdentifier.builder().key(fileName).build());
            }
        }

        // AWS S3 allows only 1000 objects per request
        final int batchSize = 1000;
        for (int i = 0; i < objectIdentifiers.size(); i += batchSize) {
            int end = Math.min(i + batchSize, objectIdentifiers.size());
            List<ObjectIdentifier> batch = objectIdentifiers.subList(i, end);

            DeleteObjectsRequest deleteRequest = DeleteObjectsRequest.builder()
                    .bucket(BUCKET_NAME)
                    .delete(Delete.builder().objects(batch).build())
                    .build();

            s3Client.deleteObjects(deleteRequest);
        }
    }
    
    
    
    public void deleteFilesNotInList(List<String> validFileNames) {
    	try {
        Set<String> validSet = validFileNames.stream()
            .filter(name -> name != null && !name.trim().isEmpty())
            .collect(Collectors.toSet());

        // List all objects in the bucket
        ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
            .bucket(BUCKET_NAME)
            .build();

        ListObjectsV2Response listResponse = s3Client.listObjectsV2(listRequest);
        
        List<String> availableFiles = listResponse.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
        System.out.println("s3 availableFiles="+availableFiles);
        System.out.println("s3 availableFiles size="+availableFiles.size());
        

        List<String> deletingFiles = listResponse.contents().stream()
            .map(S3Object::key)
            .filter(key -> !validSet.contains(key)) // Exclude valid keys
          //  .map(key -> ObjectIdentifier.builder().key(key).build())
            .collect(Collectors.toList());
        System.out.println("s3 deletingFiles="+deletingFiles);
        System.out.println("s3 deletingFiles size="+deletingFiles.size());
        

        List<String> okFiles = listResponse.contents().stream()
            .map(S3Object::key)
            .filter(key -> validSet.contains(key)) // Exclude valid keys
          //  .map(key -> ObjectIdentifier.builder().key(key).build())
            .collect(Collectors.toList());
        System.out.println("s3 okFiles="+okFiles);
        System.out.println("s3 okFiles size="+okFiles.size());

       /*
        List<ObjectIdentifier> objectsToDelete = listResponse.contents().stream()
            .map(S3Object::key)
            .filter(key -> !validSet.contains(key)) // Exclude valid keys
            .map(key -> ObjectIdentifier.builder().key(key).build())
            .collect(Collectors.toList());

        if (!objectsToDelete.isEmpty()) {
            DeleteObjectsRequest deleteRequest = DeleteObjectsRequest.builder()
                .bucket(BUCKET_NAME)
                .delete(Delete.builder().objects(objectsToDelete).build())
                .build();

            //s3Client.deleteObjects(deleteRequest);
            
            System.out.println("Deleted " + objectsToDelete.size() + " unused files.");
        } else {
            System.out.println("No unused files found to delete.");
        }
        */
        
        List<ObjectIdentifier> objectIdentifiers = new ArrayList<>();

        for (String fileName : deletingFiles) {
            if (fileName != null && !fileName.trim().isEmpty()) {
                objectIdentifiers.add(ObjectIdentifier.builder().key(fileName).build());
            }
        }

        // AWS S3 allows only 1000 objects per request
        final int batchSize = 1000;
        for (int i = 0; i < objectIdentifiers.size(); i += batchSize) {
            int end = Math.min(i + batchSize, objectIdentifiers.size());
            List<ObjectIdentifier> batch = objectIdentifiers.subList(i, end);

            DeleteObjectsRequest deleteRequest = DeleteObjectsRequest.builder()
                    .bucket(BUCKET_NAME)
                    .delete(Delete.builder().objects(batch).build())
                    .build();

           // s3Client.deleteObjects(deleteRequest);
            System.out.println("Deleted i="+i);
        }
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		ex.printStackTrace();
    	}
    }
}