DROP TABLE IF EXISTS Comment;
DROP TABLE IF EXISTS Likes;
DROP TABLE IF EXISTS Post;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id              INTEGER AUTO_INCREMENT PRIMARY KEY,
    email           VARCHAR(30),
    name            VARCHAR(30),
    username        VARCHAR(30),
    password        VARCHAR(30),
    profile_image_url VARCHAR(30),
    website         VARCHAR(30),
    bio             VARCHAR(30),
    phone_number     VARCHAR(30)
);

CREATE TABLE Post (
    id         INTEGER IDENTITY AUTO_INCREMENT PRIMARY KEY,
    imageURL   varchar(30),
    createdTime CURRENT_DATE,
    caption    varchar(30),
    location   varchar(30),
    user_id    INTEGER NOT NULL
);
ALTER TABLE Post ADD CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES Users (id);

CREATE TABLE Comment (
    id      INTEGER IDENTITY PRIMARY KEY,
    text    VARCHAR(30),
    post_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    createdTime CURRENT_DATE
);
ALTER TABLE Comment ADD CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES Post (id);
ALTER TABLE Comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES Users (id);

CREATE TABLE Likes (
    id      INTEGER IDENTITY PRIMARY KEY,
    post_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL
);
ALTER TABLE Likes ADD CONSTRAINT fk_likes_post FOREIGN KEY (post_id) REFERENCES Post (id);
ALTER TABLE Likes ADD CONSTRAINT fk_likes_user FOREIGN KEY (user_id) REFERENCES Users (id);

/*
CREATE TABLE FOLLOW (
    toUser INTEGER NOT NULL,
    fromUser INTEGER NOT NULL,
    constraint pk_follow PRIMARY KEY (toUser, fromUser)
);*/