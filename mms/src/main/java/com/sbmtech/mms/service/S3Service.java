package com.sbmtech.mms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sbmtech.mms.dto.S3DownloadDto;
import com.sbmtech.mms.dto.S3UploadDto;
import com.sbmtech.mms.dto.S3UploadObjectDto;

public interface S3Service {

	public String uploadFile(MultipartFile file) throws IOException;


    
    //public LinkedList<String> uploadBase64Images(Integer subscriberId,List<String> base64Images) ;
    public Map<String,String> uploadBase64UnitImages(Integer subscriberId,Integer unitId,Map<String,String> mapBase64Files) ;

	public List<String> listFiles();

	public String generatePresignedUrl(Integer subscriberId,Integer buildingId,Integer unitId, String fileName);
	
	public String generatePresignedUrl(S3DownloadDto s3DownloadDto);
	
	public List <S3UploadObjectDto> upload(S3UploadDto s3UploadDto);
}