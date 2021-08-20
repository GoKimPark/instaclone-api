package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"to_user", "from_user"})
)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;

    @Builder
    public Follow(User toUser, User fromUser){
        this.toUser = toUser;
        this.fromUser = fromUser;
    }
}
