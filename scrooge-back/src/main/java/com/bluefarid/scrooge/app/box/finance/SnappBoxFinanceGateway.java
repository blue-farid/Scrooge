package com.bluefarid.scrooge.app.box.finance;

import com.bluefarid.scrooge.app.box.exception.SnappBoxException;
import com.bluefarid.scrooge.app.box.request.FinanceRequest;
import com.bluefarid.scrooge.app.box.response.FinanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SnappBoxFinanceGateway {
    private final RestTemplate restTemplate;

    public FinanceResponse finance(FinanceRequest request, String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token);
        HttpEntity<FinanceRequest> entity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<FinanceResponse> response = restTemplate.exchange(
                "https://app.snapp-box.com/api/v1/customer/transaction_history", HttpMethod.POST, entity, FinanceResponse.class);

        if (response.getStatusCode().is2xxSuccessful())
            return response.getBody();
        else
            throw new SnappBoxException();
    }
}
