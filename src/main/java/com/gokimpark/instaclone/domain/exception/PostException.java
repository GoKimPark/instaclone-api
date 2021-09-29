package com.gokimpark.instaclone.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostException extends RuntimeException{
    public PostException() { super("해당 게시물을 찾을 수 없음.");}
    public PostException(String message) {super(message);}
}
