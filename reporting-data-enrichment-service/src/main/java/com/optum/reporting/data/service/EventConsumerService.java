package com.optum.reporting.data.service;


import com.optum.reporting.data.repository.MongoDataRepository;
import com.optum.reporting.data.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * EventConsumerService class used to consume events for given kafka topics.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to UHC E&A Team. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
@EnableBinding(TopicChannels.class)
public class EventConsumerService {

    @Value("${report.claimscoll}")
    private String claimsCollection;

    @Value("${report.membercoll}")
    private String memberCollection;

    @Value("${report.membergroupcoll}")
    private String memberGroupCollection;

    @Autowired
    private MongoDataRepository mongoDataRepository;


    @StreamListener
    @SuppressWarnings("unchecked")
    public void consumeClaimsComposite(@Input(TopicChannels.CLAIMS_DATA) KStream<String, GenericRecord> claimCompositeStream) throws Exception {
        log.info("Inside consumeClaimsComposite : {}", System.lineSeparator());

        /** Process and compute messages from streaming. */
        claimCompositeStream.foreach((key, value) -> {
            log.info("Reading claims data from kafka streams and ingesting data into mongoDB.");
            try {
                /* Update the kafka stream message to by adding _id as claimID as primary (partition) key required field for mongoDB. */
                Map<String, Object> claimsReportData = JsonUtils.deserializeJson(Map.class, value.toString());
                claimsReportData.put("_id", claimsReportData.get("claimID"));

                /* Ingest the claims report data into mongoDB. */
                mongoDataRepository.saveData(claimsCollection, claimsReportData);
            } catch (Exception ex) {
                log.error("Issues during ingesting claim data: {}", ExceptionUtils.getStackTrace(ex));
            }
        });
    }


    @StreamListener
    @SuppressWarnings("unchecked")
    public void consumeMemberComposite(@Input(TopicChannels.MEMBER_DATA) KStream<String, GenericRecord> memberCompositeStream) throws Exception {
        log.info("Inside consumeMemberComposite : {}", System.lineSeparator());

        memberCompositeStream.foreach((key, value) -> {
            log.info("Reading member data from kafka streams and ingesting data into mongoDB.");
            try {
                /* Update the kafka stream message to by adding _id as memberID as primary (partition) key required field for mongoDB. */
                String memberData = value.toString();
                Map<String, Object> memberReportData = JsonUtils.deserializeJson(Map.class, memberData);
                memberReportData.put("_id", memberReportData.get("memberID"));

                /* Ingest the member data into mongoDB. */
                mongoDataRepository.saveData(memberCollection, memberReportData);
            } catch (Exception ex) {
                log.error("Issues during ingesting member data: {}", ExceptionUtils.getStackTrace(ex));
            }
        });
    }



    @StreamListener
    @SuppressWarnings("unchecked")
    public void consumeMemberGroupComposite(@Input(TopicChannels.MEMBER_GROUP_DATA) KStream<String, GenericRecord> mbrgroupCompositeStream) throws Exception {
        log.info("Inside consumeMemberGroupComposite : {}", System.lineSeparator());

        mbrgroupCompositeStream.foreach((key, value) -> {
            log.info("Reading member data from kafka streams and ingesting data into mongoDB.");
            try {

                /* Update the kafka stream message to by adding _id as memberID as primary (partition) key required field for mongoDB. */
                String memberGroupData = value.toString();
                Map<String, Object> memberGroupReportData = JsonUtils.deserializeJson(Map.class, memberGroupData);
                memberGroupReportData.put("_id", memberGroupReportData.get("memGroupID"));

                /* Ingest the member group data into mongoDB. */
                mongoDataRepository.saveData(memberGroupCollection, memberGroupReportData);
            } catch (Exception ex) {
                log.error("Issues during ingesting member group data: {}", ExceptionUtils.getStackTrace(ex));
            }
        });
    }


}
