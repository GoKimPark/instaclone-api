DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(30),
    name VARCHAR(30),
    username VARCHAR(30),
    password VARCHAR(30),
    profileImageUrl VARCHAR(30),
    webSite VARCHAR(30),
    bio VARCHAR(30),
    phoneNumber VARCHAR(30)
) engine=InnoDB;

DROP TABLE IF EXISTS Post;
CREATE TABLE Post (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    imageURL varchar(30),
    createDate Date,
    caption varchar(30),
    location varchar(30),
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id)
) engine=InnoDB;

DROP TABLE IF EXISTS Comment;
CREATE TABLE Comment (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    text varchar(30),
    post_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Post(id),
    FOREIGN KEY (user_id) REFERENCES Users(id)
) engine=InnoDB;

DROP TABLE IF EXISTS Likes;
CREATE TABLE Likes (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    post_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Post(id),
    FOREIGN KEY (user_id) REFERENCES Users(id)
) engine=InnoDB;