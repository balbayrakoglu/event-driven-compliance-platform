package com.example.aml.config;

import com.example.common.kafka.TopicConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerErrorConfig {

    @Bean
    public DefaultErrorHandler kafkaErrorHandler(
            KafkaTemplate<Object, Object> kafkaTemplate
    ) {

        DeadLetterPublishingRecoverer recoverer =
                new DeadLetterPublishingRecoverer(
                        kafkaTemplate,
                        this::resolveDeadLetterTopic
                );

        // retry every 5 seconds, max 3 attempts
        FixedBackOff backOff = new FixedBackOff(5_000L, 3);

        DefaultErrorHandler errorHandler =
                new DefaultErrorHandler(recoverer, backOff);

        // Retryable (transient)
        errorHandler.addRetryableExceptions(
                RuntimeException.class
        );

        // Non-retryable (permanent)
        errorHandler.addNotRetryableExceptions(
                IllegalArgumentException.class
        );

        return errorHandler;
    }

    private TopicPartition resolveDeadLetterTopic(
            ConsumerRecord<?, ?> record,
            Exception exception
    ) {

        // Route to domain-specific DLT
        if (record.topic().startsWith("transaction.events")) {
            return new TopicPartition(
                    TopicConstants.TRANSACTION_EVENTS_DLT,
                    record.partition()
            );
        }

        // Fallback (should not normally happen)
        return new TopicPartition(
                TopicConstants.COMPLIANCE_EVENTS_DLT,
                record.partition()
        );
    }
}
