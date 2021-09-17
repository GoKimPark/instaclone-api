DROP TABLE Users IF EXISTS;
DROP TABLE Post IF EXISTS;
DROP TABLE Comment IF EXISTS;
DROP TABLE Likes IF EXISTS;
DROP TABLE Follow IF EXISTS;

CREATE TABLE Users (
                       id              INTEGER IDENTITY PRIMARY KEY,
                       email           VARCHAR(30),
                       name            VARCHAR(30),
                       username        VARCHAR(30),
                       password        VARCHAR(30),
                       profileImageUrl VARCHAR(30),
                       webSite         VARCHAR(30),
                       bio             VARCHAR(30),
                       phoneNumber     VARCHAR(30)
);

CREATE TABLE Post (
                      id       INTEGER IDENTITY PRIMARY KEY,
                      imageURL varchar(30),
                      user     INTEGER NOT NULL,
                      caption  varchar(30),
                      location varchar(30)
);
ALTER TABLE Post ADD CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES Users (id);

CREATE TABLE Comment (
                         id   INTEGER IDENTITY PRIMARY KEY,
                         text VARCHAR(30),
                         post INTEGER NOT NULL,
                         user INTEGER NOT NULL
);
ALTER TABLE Comment ADD CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES Post (id);
ALTER TABLE Comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES Users (id);

CREATE TABLE Likes (
                       id   INTEGER IDENTITY PRIMARY KEY,
                       post INTEGER NOT NULL,
                       user INTEGER NOT NULL
);
ALTER TABLE Likes ADD CONSTRAINT fk_likes_post FOREIGN KEY (post_id) REFERENCES Post (id);
ALTER TABLE Likes ADD CONSTRAINT fk_likes_user FOREIGN KEY (user_id) REFERENCES Users (id);

CREATE TABLE FOLLOW (
                        toUser INTEGER NOT NULL,
                        fromUser INTEGER NOT NULL,
                        constraint pk_follow PRIMARY KEY (toUser, fromUser)
);