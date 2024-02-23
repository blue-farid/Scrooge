package com.bluefarid.scrooge.app.box.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String amount;
    private String type;
    private String transactionStatus;
}
