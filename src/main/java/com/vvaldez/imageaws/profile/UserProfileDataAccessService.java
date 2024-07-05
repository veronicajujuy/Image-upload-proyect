package com.vvaldez.imageaws.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class UserProfileDataAccessService {
    private final UserRepository userRepository;

    List<UserProfile> getUserProfile(){
        return userRepository.findAll();
    }
    public Optional<UserProfile> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public void updateUserProfile(UserProfile userProfile) {
        userRepository.save(userProfile);
    }
}
