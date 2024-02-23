package com.bluefarid.scrooge.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HistoryResponse {
    private Double totalDebit = 0D;
}
