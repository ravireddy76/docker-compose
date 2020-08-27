package com.poc.gql.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "claims")
public class Claim {

    @Id
    private String id;
    private String type;
    private String provider;
    private String icdCode;
    private String cptCode;
    private Date clmDate;
    private Date processDate;
    private double paidAmount;
    private double claimAmount;
}
