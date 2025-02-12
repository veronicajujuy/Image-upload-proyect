package com.vvaldez.imageaws.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user-profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfile();
    }

    @PostMapping(
            path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId")UUID userProfileId,
                                       @RequestParam("file")MultipartFile file){
        userProfileService.uploadUserProfileImage(userProfileId, file );
    }

    @GetMapping("{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId")UUID userProfileId){
        return userProfileService.downloadUserProfileImage(userProfileId);
    }
}
