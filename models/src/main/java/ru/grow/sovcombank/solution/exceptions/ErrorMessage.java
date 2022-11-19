package ru.grow.sovcombank.solution.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    private final Date createdAt;
    private final Integer statusCode;
    private final String statusMessage;
    private final String message;

    public ErrorMessage(Date createdAt, HttpStatus status, String message) {
        this.createdAt = createdAt;
        this.statusCode = status.value();
        this.statusMessage = status.getReasonPhrase();
        this.message = message;
    }
}
