Table users as U {
  id Integer [pk, increment] // auto-increment
  email String
  name String
  username String
  password String
  profileImageUrl String
  website String
  bio String
  phoneNumber String
}

Table Post as P {
  id Integer [pk, increment]
  imageUrl String
  createdTime LocalDate
  caption String
  location String
  user User [ref: > U.id]
}

Table Comment as C {
  id Integer [pk, increment]
  text String
  post Post [ref: > P.id]
  user User [ref: > U.id]
}

Table Follow as F {
  toUser Integer [pk]
  fromUser Integer [pk]
}

Table Likes as L {
  id Integer [pk, increment]
  post Post [ref: > P.id]
  user User [ref: > U.id]
}