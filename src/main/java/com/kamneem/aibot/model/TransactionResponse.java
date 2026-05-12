package com.kamneem.aibot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String txnId;
    private String status;
    private Integer amount;
    private String userId;
    private String paymentMode;
    private Date timestamp;
}