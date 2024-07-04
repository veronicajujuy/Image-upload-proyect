package com.vvaldez.imageaws.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String bucketName,
                     String path,
                     String filename,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream){
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if(!map.isEmpty()){
                map.forEach(metadata::addUserMetadata);
            }
        });
        try{
            String fullPath = String.format("%s/%s", path, filename);
            s3.putObject(bucketName, fullPath,inputStream, metadata);

        }catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }

    public byte[] download(String path, String prefix, String key) {
        try{
            String fullpath = String.format("%s/%s", prefix,key);
           S3Object object = s3.getObject(path, fullpath);
           return IOUtils.toByteArray(object.getObjectContent());

        }catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to download file to s3", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
