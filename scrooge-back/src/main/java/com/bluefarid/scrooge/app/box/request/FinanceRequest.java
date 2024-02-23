package com.bluefarid.scrooge.app.box.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FinanceRequest {
    private String phoneNumber;
    private String pageNumber;
    private String pageSize;
    private boolean includeAsanPardakhtWallet = true;
}
