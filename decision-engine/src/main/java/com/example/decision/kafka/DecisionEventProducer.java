package com.example.decision.kafka;


import com.example.common.event.DecisionMadeEvent;
import com.example.common.kafka.EventHeaders;
import com.example.common.kafka.SchemaVersion;
import com.example.common.kafka.TopicConstants;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DecisionEventProducer {

    private final KafkaTemplate<String, DecisionMadeEvent> kafkaTemplate;

    public DecisionEventProducer(KafkaTemplate<String, DecisionMadeEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(DecisionMadeEvent event) {

        ProducerRecord<String, DecisionMadeEvent> record =
                new ProducerRecord<>(
                        TopicConstants.DECISION_EVENTS_V1,
                        event.getTransactionId(),
                        event
                );

        record.headers().add(EventHeaders.CORRELATION_ID,
                event.getCorrelationId().getBytes());

        record.headers().add(EventHeaders.EVENT_TYPE,
                event.getClass().getName().getBytes());

        record.headers().add(EventHeaders.SCHEMA_VERSION,
                SchemaVersion.V1.value().getBytes());

        record.headers().add(EventHeaders.PRODUCED_AT,
                String.valueOf(Instant.now().toEpochMilli()).getBytes());

        kafkaTemplate.send(record);
    }
}
