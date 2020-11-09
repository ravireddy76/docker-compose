package com.optum.reporting.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDetailTracker {

    private List<MemberSubscriberDetails> empMembers = new ArrayList<>();
    private List<MemberSubscriberDetails> espMembers = new ArrayList<>();
    private List<MemberSubscriberDetails> echMembers = new ArrayList<>();
    private List<MemberSubscriberDetails> famMembers = new ArrayList<>();
}
