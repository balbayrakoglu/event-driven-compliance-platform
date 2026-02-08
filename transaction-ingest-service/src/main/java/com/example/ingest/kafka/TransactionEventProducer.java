package com.example.ingest.kafka;

import com.example.common.event.TransactionReceivedEvent;
import com.example.common.kafka.EventHeaders;
import com.example.common.kafka.SchemaVersion;
import com.example.common.kafka.TopicConstants;
import com.example.ingest.api.TransactionRequest;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Component
public class TransactionEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public TransactionEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(TransactionRequest request, String correlationId) {

        TransactionReceivedEvent event =
                new TransactionReceivedEvent(
                        correlationId,
                        request.transactionId(),
                        request.amount(),
                        request.currency(),
                        request.accountId()
                );

        ProducerRecord<String, Object> record =
                new ProducerRecord<>(
                        TopicConstants.TRANSACTION_EVENTS_V1,
                        event.getTransactionId(),
                        event
                );

        // -------- headers --------
        record.headers().add(
                EventHeaders.CORRELATION_ID,
                correlationId.getBytes(StandardCharsets.UTF_8)
        );
        record.headers().add(
                EventHeaders.EVENT_TYPE,
                event.getClass().getName().getBytes(StandardCharsets.UTF_8)
        );
        record.headers().add(
                EventHeaders.SCHEMA_VERSION,
                SchemaVersion.V1.value().getBytes(StandardCharsets.UTF_8)
        );
        record.headers().add(
                EventHeaders.PRODUCED_AT,
                String.valueOf(Instant.now().toEpochMilli())
                        .getBytes(StandardCharsets.UTF_8)
        );

        // -------- async send (Spring Kafka 3.x) --------
        kafkaTemplate.send(record)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        // THIS is where your previous 500 was hiding
                        throw new RuntimeException(
                                "Failed to publish TransactionReceivedEvent",
                                ex
                        );
                    }
                });
    }
}
