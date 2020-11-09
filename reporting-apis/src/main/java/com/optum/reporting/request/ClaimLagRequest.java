package com.optum.reporting.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClaimLagRequest {

    private String memberGroupId;
    private String bookYear;
}
