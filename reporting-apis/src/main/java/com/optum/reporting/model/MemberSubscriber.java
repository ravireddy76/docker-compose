package com.optum.reporting.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSubscriber {

    private String memberGroup;
    private String benefitType;
    private String membershipDate;
    private int singleSubscribers;
    private int subscribersSpouse;
    private int subscribersChild;
    private int subscribersFamily;
    private int totalSubscribers;
    private int totalMembers;
}
