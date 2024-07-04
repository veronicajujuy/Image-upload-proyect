package com.vvaldez.imageaws.datastore;

import com.vvaldez.imageaws.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("dae86d46-6e1b-451d-9d52-a309f19ed9f5"), "juanperez", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("2d3f63df-f521-4130-a4ff-ea2752af06b1"), "lucianagarcia", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
    public Optional<UserProfile> getUserById(UUID id){
        UserProfile userToReturn = null;
        for(UserProfile user: USER_PROFILES){
            if(user.getUserProfileId().equals(id)){
                userToReturn = user;
                break;
            }
        }
        return Optional.ofNullable(userToReturn);
    }
}
