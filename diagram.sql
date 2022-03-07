Table users as U {
  id Long [pk, increment]
  email String
  name String
  username String
  password String
  website String
}

Table Post as P {
  id Long [pk, increment]
  imageUrl String
  createdTime LocalDate
  comments Comment
  user Long [ref: > U.id]
}

Table Comment as C {
  id Long [pk, increment]
  text String
  post Long [ref: > P.id]
  user Long [ref: > U.id]
  createdTime LocalDate
}

Table Follow as F {
  toUser Long [pk, ref: > U.id]
  fromUser Long [pk, ref: > U.id]
}

Table Likes as L {
  id Long [pk, increment]
  post Long [ref: > P.id]
  user Long [ref: > U.id]
}