package com.poc.gql.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "members")
public class Member {

    @Id
    private String id;
    private String groupId;
    private String fname;
    private String lname;
    private String address;
    private List<String> claims;
}
