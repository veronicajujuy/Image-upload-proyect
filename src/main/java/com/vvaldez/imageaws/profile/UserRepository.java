package com.vvaldez.imageaws.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserProfile, UUID> {
}
