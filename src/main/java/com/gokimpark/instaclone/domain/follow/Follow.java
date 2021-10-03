package com.gokimpark.instaclone.domain.follow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Getter
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"to_user", "from_user"})
)
@IdClass(Follow.PK.class)
public class Follow {

    @Id
    @Column(name = "to_user", insertable = false, updatable = false)
    private Integer toUser;

    @Id
    @Column(name = "from_user", insertable = false, updatable = false)
    private Integer fromUser;

    public Follow(Integer toUser, Integer fromUser) {
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    public static class PK implements Serializable {
        Integer toUser;
        Integer fromUser;
    }
}
