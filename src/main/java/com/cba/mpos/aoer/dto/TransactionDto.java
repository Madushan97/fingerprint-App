package com.cba.mpos.aoer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    private Integer id;
    private String tid;
    private String mid;
    private String merchantName;
    private BigDecimal amount;
    private String timestamp;
    private String paymentMode;
    private String custMobile;
    private String transType;
    private String cardLabel;
    private String currency;
    private String pan;
    private String signData;
    private String entryMode;
    private Boolean isSettled;
    private Boolean isAway;
}
