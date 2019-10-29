package com.webbfontaine.task.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    protected String responseMessage;

    protected String error;

    public ServiceException(final String message, final String responseMessage, final String error) {
        super(message);
        this.error = error;
        this.responseMessage = responseMessage;
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final Exception exception) {
        super(exception);
    }

    public ServiceException(final String message, final String responseMessage, final Throwable cause) {
        super(message, cause);
        this.responseMessage = responseMessage;
    }

    public ServiceException(final String message, final String responseMessage) {
        super(message);
        this.responseMessage = responseMessage;
    }

    public ServiceException(final String responseMessage, final Throwable cause) {
        super(cause);
        this.responseMessage = responseMessage;
    }
}
