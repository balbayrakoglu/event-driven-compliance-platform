package com.example.aml.kafka;

import com.example.aml.service.AmlCheckService;
import com.example.aml.service.ProcessedEventStore;
import com.example.common.event.TransactionReceivedEvent;
import com.example.common.kafka.TopicConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AmlEventConsumer {

    private final AmlCheckService amlCheckService;
    private final ProcessedEventStore processedEventStore;

    public AmlEventConsumer(
            AmlCheckService amlCheckService,
            ProcessedEventStore processedEventStore
    ) {
        this.amlCheckService = amlCheckService;
        this.processedEventStore = processedEventStore;
    }

    @KafkaListener(
            topics = TopicConstants.TRANSACTION_EVENTS_V1,
            groupId = "aml-service"
    )
    public void consume(
            TransactionReceivedEvent event,
            Acknowledgment ack
    ) {
        try {
            amlCheckService.evaluate(event);
            processedEventStore.markProcessed(event.getEventId());
            ack.acknowledge();

        } catch (DataIntegrityViolationException e) {
            log.info("Duplicate event skipped: {}", event.getEventId());
            ack.acknowledge();

        } catch (Exception e) {
            log.error("Error processing event {}", event.getEventId(), e);
            throw e;
        }
    }
}
