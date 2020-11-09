package com.optum.reporting.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSubscriberRequest {

    private String memberGroupId;
    private String govtBenefitType;
}
