package com.poc.kafka.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BillingSummary {

    private String memberId;
    private int numberOfClaims;
    private double totalAmount;
}
