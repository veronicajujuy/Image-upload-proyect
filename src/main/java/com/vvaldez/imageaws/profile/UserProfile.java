package com.vvaldez.imageaws.profile;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile implements Serializable {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userProfileId;
    private String username;
    private String userProfileLink; // S3 key
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
