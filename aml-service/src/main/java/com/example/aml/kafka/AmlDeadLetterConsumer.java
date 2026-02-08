package com.example.aml.kafka;

import com.example.common.event.TransactionReceivedEvent;
import com.example.common.kafka.TopicConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AmlDeadLetterConsumer {

    @KafkaListener(
            topics = TopicConstants.TRANSACTION_EVENTS_DLT,
            groupId = "aml-service-dlt"
    )
    public void consumeFromDlt(TransactionReceivedEvent event) {

        // In real systems:
        // - persist for investigation
        // - alert ops / compliance team
        // - support manual replay

        System.err.println(
                "DLT event received. transactionId=" + event.getTransactionId()
        );
    }
}
