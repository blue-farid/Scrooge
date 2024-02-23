package com.bluefarid.scrooge.app.box.service;

import com.bluefarid.scrooge.app.box.auth.SnappBoxAuthGateway;
import com.bluefarid.scrooge.app.box.dto.TransactionDto;
import com.bluefarid.scrooge.app.box.finance.SnappBoxFinanceGateway;
import com.bluefarid.scrooge.app.box.request.FinanceRequest;
import com.bluefarid.scrooge.app.box.request.LoginRequest;
import com.bluefarid.scrooge.app.box.request.OtpRequest;
import com.bluefarid.scrooge.app.box.response.FinanceResponse;
import com.bluefarid.scrooge.app.box.response.LoginResponse;
import com.bluefarid.scrooge.response.HistoryResponse;
import com.bluefarid.scrooge.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SnappBoxService {
    private final SnappBoxAuthGateway authGateway;
    private final SnappBoxFinanceGateway financeGateway;
    private final RedisUtil redisUtil;

    public void otp(OtpRequest request) {
        authGateway.otp(request);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse loginResponse = authGateway.login(loginRequest);
        redisUtil.save(loginRequest.getPhoneNumber(), loginResponse.getToken());
        return loginResponse;
    }

    public HistoryResponse finance(FinanceRequest request) {
        FinanceResponse financeResponse = financeGateway.finance(request, redisUtil.getValue(request.getPhoneNumber()));
        if (financeResponse.getTotalPageCount() > 1) {
            int totalCount = financeResponse.getTotalPageCount();
            HistoryResponse historyResponse = new HistoryResponse();
            for (int i = 2; i <= totalCount; i++) {
                financeResponse = financeGateway.finance(request.setPageNumber(String.valueOf(i)), redisUtil.getValue(request.getPhoneNumber()));
                calculateHistory(financeResponse.getTransactionList(), historyResponse);
            }
            return historyResponse;
        } else {
            return calculateHistory(financeResponse.getTransactionList(), new HistoryResponse());
        }
    }

    private HistoryResponse calculateHistory(List<TransactionDto> transactions, HistoryResponse response) {
        transactions.stream().filter(t -> t.getTransactionStatus().equals("COMPLETED") &&
                t.getType().equals("DEBIT")).forEach(
                        t -> {
                            response.setTotalDebit(
                                    Double.sum(Double.parseDouble(
                                            t.getAmount()), response.getTotalDebit())
                            );
                        }
        );
        return response;
    }
 }
