package com.clarit.hs.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String message;
    private String details;

    /**
     * @param message
     *
     */
    public ForbiddenException(String message, String details, Throwable e) {
        super(e);
        this.message = message;
        this.details = details;
    }

    public ForbiddenException(String message, String details) {
        this.message = message;
        this.details = details;
    }




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}