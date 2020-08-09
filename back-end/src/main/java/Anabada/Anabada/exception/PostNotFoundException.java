package Anabada.Anabada.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PostNotFoundException extends RuntimeException {

    private static final long Version=1L;

    public PostNotFoundException(String message){
        super(message);
    }

    public PostNotFoundException(String message,Throwable throwable){
        super(message,throwable);
    }

}
