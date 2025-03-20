package com.sbmtech.mms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

	public String uploadFile(MultipartFile file) throws IOException;

	public Map<String, String> uploadBase64Images(Integer subscriberId, Map<String, String> mapBase64Files);

	public List<String> listFiles();

	public String generatePresignedUrl(Integer id, String fileName);
}