package com.ayush.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {

    String message;
    String success;

    public ApiResponse(String message, String success) {
        this.message = message;
        this.success = success;
    }
}
