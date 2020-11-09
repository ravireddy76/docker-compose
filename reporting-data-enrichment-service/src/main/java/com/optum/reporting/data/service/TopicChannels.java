package com.optum.reporting.data.service;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface TopicChannels {

    String CLAIMS_DATA =  "cirrus-report-claim";
    String MEMBER_DATA =  "composite-member-data";
    String MEMBER_GROUP_DATA =  "composite-membergroup-data";

    /** Input Bindings */
    @Input(CLAIMS_DATA)
    KStream<String, GenericRecord> claimData();

    @Input(MEMBER_DATA)
    KStream<String, GenericRecord> memberData();

    @Input(MEMBER_GROUP_DATA)
    KStream<String, GenericRecord> memberGroupData();
}
