package com.optum.reporting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClaimsLag {

    private String serviceDate;
    private Double totalPaidAmount;
}
