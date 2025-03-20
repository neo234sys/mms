package com.sbmtech.mms.service;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


public interface S3Service {

   
   

    public String uploadFile(MultipartFile file) throws IOException ;
    
    //public LinkedList<String> uploadBase64Images(Integer subscriberId,List<String> base64Images) ;
    public Map<String,String> uploadBase64Images(Integer subscriberId,Map<String,String> mapBase64Files) ;

    public List<String> listFiles() ;
    
    //public Map<String, String> getFilesWithPresignedUrls(List<String> fileNames) ;
    
    public String generatePresignedUrl(Integer id,String fileName) ;
}