package com.bluefarid.scrooge.app.box.response;

import com.bluefarid.scrooge.app.box.dto.TransactionDto;
import lombok.Data;

import java.util.List;

@Data
public class FinanceResponse {
    private List<TransactionDto> transactionList;
    private Integer totalPageCount;
}
