package com.sbmtech.mms.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbmtech.mms.dto.S3DownloadDto;
import com.sbmtech.mms.dto.S3UploadDto;
import com.sbmtech.mms.dto.S3UploadObjectDto;
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
	
	@Value("${mms.app.s3Bucket}")
	private String bucketName;

	//private final String bucketName = "mms-dev-bucket-19032025";
	//private final String bucketName = "mms-local-bucket-29042025";

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
		String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(fileName)
				.contentType(file.getContentType()).build();

		s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

		return fileName; // Return the generated file name
	}

	@Override
	public List<String> listFiles() {
		ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder().bucket(bucketName).build();

		ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);

		return listObjectsV2Response.contents().stream().map(S3Object::key).collect(Collectors.toList());
	}

	public Map<String, String> uploadBase64UnitImages(Integer subscriberId, Integer unitId,
			Map<String, String> mapBase64Files) {

		Map<String, String> mapFileNames = new HashMap<>();

		for (Map.Entry<String, String> entry : mapBase64Files.entrySet()) {
			try {
				if (StringUtils.isNotBlank(entry.getValue())) {
					String base64Image = entry.getValue();
					if (base64Image.contains(",")) {
						base64Image = base64Image.split(",")[1];
					}
					byte[] imageBytes = Base64.getDecoder().decode(base64Image);
					String fileName = "S_" + UUID.randomUUID() + "_" + unitId + ".jpg";
					PutObjectRequest putRequest = PutObjectRequest.builder().bucket(bucketName).key(fileName)
							.contentType("image/jpeg").build();
					s3Client.putObject(putRequest, RequestBody.fromBytes(imageBytes));
					mapFileNames.put(entry.getKey(), fileName);
				}
			} catch (Exception ex) {
				logger.error("Failed to upload image unitId=", unitId);
			}
		}

		return mapFileNames;
	}
//	 public Map<String, String> getFilesWithPresignedUrls(List<String> fileNames) {
//	        return fileNames.stream()
//	                .collect(Collectors.toMap(fileName -> fileName, this::generatePresignedUrl));
//	 }

	public String generatePresignedUrl(Integer subscriberId, Integer buildingId, Integer unitId, String fileName) {
		String fileUrl = "";
		try {
			GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(fileName).build();

			GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
					.signatureDuration(Duration.ofHours(1)) // URL valid for 1 hour
					.getObjectRequest(getObjectRequest).build();

			PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
			fileUrl = presignedRequest.url().toString();
		} catch (Exception ex) {
			logger.error("Failed to upload image unitId={} filename={}", unitId, fileName);
		}
		return fileUrl;
	}

	@Override
	public List<S3UploadObjectDto> upload(S3UploadDto s3UploadDto) {

		List<S3UploadObjectDto> s3UploadObjectDtolist = s3UploadDto.getS3UploadObjectDtoList();
		for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtolist) {

			try {
				if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getObjectBase64())) {
					String base64Image = s3UploadObjectDto.getObjectBase64();
					if (base64Image.contains(",")) {
						base64Image = base64Image.split(",")[1];
					}
					byte[] imageBytes = Base64.getDecoder().decode(base64Image);
					String fileName = "S_" + s3UploadDto.getSubscriberId() + "#" + UUID.randomUUID() + "#B_"
							+ s3UploadDto.getBuildingId() + "#U_" + s3UploadDto.getUnitId() + "."
							+ s3UploadObjectDto.getFileExt();
					PutObjectRequest putRequest = PutObjectRequest.builder().bucket(bucketName).key(fileName)
							.contentType(s3UploadObjectDto.getContentType()).build();
					s3Client.putObject(putRequest, RequestBody.fromBytes(imageBytes));
					s3UploadObjectDto.setS3FileName(fileName);
					logger.info("S3 upload object Success for " + s3UploadDto.getObjectType() + ", unitId="
							+ s3UploadDto.getUnitId() + ", buildingId=" + s3UploadDto.getBuildingId()
							+ ", subscriberId=" + s3UploadDto.getSubscriberId());
				}
			} catch (Exception ex) {
				logger.error("S3 Failed to upload object for " + s3UploadDto.getObjectType() + ", unitId="
						+ s3UploadDto.getUnitId() + ", buildingId=" + s3UploadDto.getBuildingId() + ", subscriberId="
						+ s3UploadDto.getSubscriberId());
			}
		}
		return s3UploadObjectDtolist;
	}

	@Override
	public String generatePresignedUrl(S3DownloadDto s3DownloadDto) {
		String fileUrl = "";
		String fileName = "";
		try {
			fileName = s3DownloadDto.getS3FileName();
			GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(fileName).build();

			GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
					.signatureDuration(Duration.ofHours(1)) // URL valid for 1 hour
					.getObjectRequest(getObjectRequest).build();

			PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
			fileUrl = presignedRequest.url().toString();
		} catch (Exception ex) {
			logger.error("Failed to upload image subscriberId={}, buidlingId{}, unitId={} filename={}",
					s3DownloadDto.getSubscriberId(), s3DownloadDto.getBuildingId(), s3DownloadDto.getUnitId(),
					fileName);
		}
		return fileUrl;
	}

}