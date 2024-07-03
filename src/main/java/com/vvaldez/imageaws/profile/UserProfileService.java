package com.vvaldez.imageaws.profile;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileDataAccessService userProfileDataAccessService;

    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService) {
        this.userProfileDataAccessService = userProfileDataAccessService;
    }

    List<UserProfile> getUserProfile(){
        return userProfileDataAccessService.getUserProfile();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // 1. chequear si el archivo no esta vacio
        // 2. si el archivo es una imagen
        // 3. Si el usuario existe en nuestra base de datos
        // 4. Guardar alguna metadata del archivo si existe
        // 5. Guardar la imagen en s3 y hacer un update de la base de datos con la url del s3
    }
}
