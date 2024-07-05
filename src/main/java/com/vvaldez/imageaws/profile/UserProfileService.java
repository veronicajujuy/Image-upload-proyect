package com.vvaldez.imageaws.profile;

import com.amazonaws.services.s3.AmazonS3;
import com.vvaldez.imageaws.bucket.BucketName;
import com.vvaldez.imageaws.filestore.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@RequiredArgsConstructor
@Service
public class UserProfileService {
    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;
    private final AmazonS3 s3;

    List<UserProfile> getUserProfile(){
        return userProfileDataAccessService.getUserProfile();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType());
        // 1. chequear si el archivo no esta vacio

        if(file.isEmpty())
            throw new IllegalStateException("No se puede cargar un archivo vacio "+ file.getSize());
        // 2. si el archivo es una imagen
        if(!ALLOWED_IMAGE_TYPES.contains(file.getContentType()))
            throw new IllegalStateException("El archivo debe ser uma imagen");

        // 3. Si el usuario existe en nuestra base de datos
        Optional<UserProfile> userOptional = userProfileDataAccessService.getUserById(userProfileId);
        if(userOptional.isEmpty()) throw new IllegalStateException("Usuario no existente");
        // 4. Guardar alguna metadata del archivo si existe
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        // 5. Guardar la imagen en s3 y hacer un update de la base de datos con la url del s3
        UserProfile user = userOptional.get();
        String path = (user.getUserProfileId().toString());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(BucketName.PROFILE_IMAGE.getBucketName(), path, filename, Optional.of(metadata), file.getInputStream());
            user.setUserProfileLink(filename);
            userProfileDataAccessService.updateUserProfile(user);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        Optional<UserProfile> userOptional = userProfileDataAccessService.getUserById(userProfileId);
        if(userOptional.isEmpty()) throw new IllegalStateException("Usuario no existente");
        UserProfile user = userOptional.get();

        if(user.getUserProfileLink()!=null){
            String path = String.format("%s",
                    BucketName.PROFILE_IMAGE.getBucketName());
            String prefix = String.format("%s", user.getUserProfileId());
            String key = user.getUserProfileLink();
            return fileStore.download(path, prefix, key);
        } else {
            return new byte[0];
        }
    }
}
