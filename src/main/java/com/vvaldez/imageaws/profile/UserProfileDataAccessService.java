package com.vvaldez.imageaws.profile;

import com.vvaldez.imageaws.datastore.FakeUserProfileDataStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserProfileDataAccessService {
    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    public UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    List<UserProfile> getUserProfile(){
        return fakeUserProfileDataStore.getUserProfiles();
    }
    public Optional<UserProfile> getUserById(UUID id){
        return fakeUserProfileDataStore.getUserById(id);
    }
}
