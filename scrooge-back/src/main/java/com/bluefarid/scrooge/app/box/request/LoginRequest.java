package com.bluefarid.scrooge.app.box.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String otp;
    private String phoneNumber;
}
