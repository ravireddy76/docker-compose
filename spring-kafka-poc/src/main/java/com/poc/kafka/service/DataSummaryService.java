package com.poc.kafka.service;

import com.poc.kafka.model.BillingSummary;
import com.poc.kafka.model.ClaimsBilling;
import com.poc.kafka.repository.ClaimBillingRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
@Slf4j
public class DataSummaryService {

    @Autowired
    private ClaimBillingRepository claimBillingRepository;

    @Autowired
    private EventPublisher eventPublisher;

    @Value("${topics.datasummary}")
    private String dataSummaryTopic;


    @Scheduled(cron = "${scheduler.datasummarycron}")
    public void computeDataSummary() {

        /* Get or read the claims billing data from DB. */
        List<ClaimsBilling> claimsBillingData = claimBillingRepository.findAll();
        log.info("ClaimsBillingData Size: {}, Current Time: {}", claimsBillingData.size(), LocalDateTime.now());

        /*
         *  1) Compute to partition data using grouping by member id.
         *  2) Build the billing summary data.
         *  3) Publish billing summary data into kafka streams.
         */
        Map<String, List<ClaimsBilling>> partitionData = claimsBillingData.stream().collect(groupingBy(ClaimsBilling::getMemberId));
        for (Map.Entry<String, List<ClaimsBilling>> entry : partitionData.entrySet()) {
            /* Calculate total dollar amount by member id. */
            Double totalAmount = entry.getValue().stream().map(cBilling -> cBilling.getAmount()).reduce(0.0, Double::sum);

            /* Build the billing summary data. */
            BillingSummary billingSummary = BillingSummary.builder().memberId(entry.getKey())
                    .numberOfClaims(entry.getValue().size())
                    .totalAmount(totalAmount).build();
            log.info("Billing Summary: {}", billingSummary);

            /* Publish the billing summary data into kafka streams. */
            publishMessage(billingSummary);
        }
    }

    /**
     * Method to publish message into kafka streams.
     *
     * @param billingSummary
     */
    private void publishMessage(BillingSummary billingSummary) {
        try {
            eventPublisher.publish(dataSummaryTopic, billingSummary);
        } catch (Exception ex) {
            log.error("Unable to publish message to kafka streams: {}", ExceptionUtils.getStackTrace(ex));
        }
    }

}
