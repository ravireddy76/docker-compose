package com.optum.reporting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ClaimExpenses {

    private String range;
    private String numberOfClaimants;
    private String percentageClaimants;
    private String totalPayments;
    private String percentagePayments;
}
