package com.optum.reporting.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberSubscriberDetails {

    private String memberGroup;
    private String benefitType;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String completeAddress;
    private String branding;
    private List<LinkedHashMap<String, Object>> mBenefitCovLevelReportData;
}
