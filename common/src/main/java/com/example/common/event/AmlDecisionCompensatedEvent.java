package com.example.common.event;

import java.util.Objects;

public record AmlDecisionCompensatedEvent(
        EventMetadata metadata,
        String transactionId,
        String previousStatus,
        String reason
) implements DomainEvent {
    public AmlDecisionCompensatedEvent {
        Objects.requireNonNull(metadata);
        Objects.requireNonNull(transactionId);
        Objects.requireNonNull(previousStatus);
    }
}
