package com.example.crud.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
