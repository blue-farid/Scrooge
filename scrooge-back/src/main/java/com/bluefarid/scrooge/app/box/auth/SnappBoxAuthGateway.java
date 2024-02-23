package com.bluefarid.scrooge.app.box.auth;

import com.bluefarid.scrooge.app.box.exception.SnappBoxException;
import com.bluefarid.scrooge.app.box.request.LoginRequest;
import com.bluefarid.scrooge.app.box.request.OtpRequest;
import com.bluefarid.scrooge.app.box.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class SnappBoxAuthGateway {
    private final RestTemplate restTemplate;

    public void otp(OtpRequest request) {
        ResponseEntity<Void> response = restTemplate.postForEntity("https://app.snapp-box.com/api/getouei/api/customer/v2/auth/otp/send",
                request, Void.class);

        if(response.getStatusCode().is5xxServerError())
            throw new SnappBoxException();
    }

    public LoginResponse login(LoginRequest request) {
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity("https://app.snapp-box.com/api/getouei/api/customer/v2/auth/otp",
                request, LoginResponse.class);

        if (response.getStatusCode().is2xxSuccessful())
            return response.getBody();
        else
            throw new SnappBoxException();
    }

}
