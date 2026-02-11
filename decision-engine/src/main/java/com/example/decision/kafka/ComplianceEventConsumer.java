package com.example.decision.kafka;

import com.example.common.event.DecisionMadeEvent;
import com.example.common.event.DecisionStatus;
import com.example.common.event.TransactionReceivedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ComplianceEventConsumer {

    private final DecisionEventProducer producer;

    public ComplianceEventConsumer(DecisionEventProducer producer) {
        this.producer = producer;
    }

    @KafkaListener(
            topics = "transaction.events.v1",
            groupId = "decision-engine"
    )
    public void consume(TransactionReceivedEvent event) {

        // Simplified decision logic
        DecisionStatus status =
                event.amount().signum() > 0
                        ? DecisionStatus.APPROVED
                        : DecisionStatus.REJECTED;

        DecisionMadeEvent decision =
                new DecisionMadeEvent(
                        event.metadata(),
                        event.transactionId(),
                        status,
                        "Basic AML decision"
                );

        producer.publish(decision);
    }
}