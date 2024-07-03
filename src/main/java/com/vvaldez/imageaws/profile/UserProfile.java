package com.vvaldez.imageaws.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {
    private UUID userProfileId;
    private String username;
    private String userProfileLink; // S3 key

    public UserProfile(UUID userProfileId, String username, String userProfileLink) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.userProfileLink = userProfileLink;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(UUID userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<String>  getUserProfileLink() {
        return Optional.ofNullable(userProfileLink);
    }

    public void setUserProfileLink(String userProfileLink) {
        this.userProfileLink = userProfileLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId) && Objects.equals(username, that.username) && Objects.equals(userProfileLink, that.userProfileLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileLink);
    }
}
