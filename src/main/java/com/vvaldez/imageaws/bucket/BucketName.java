package com.vvaldez.imageaws.bucket;

public enum BucketName {
    PROFILE_IMAGE("image-upload-vv-01");
    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
