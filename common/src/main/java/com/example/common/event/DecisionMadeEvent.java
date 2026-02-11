package com.example.common.event;

import java.util.Objects;

public record DecisionMadeEvent(
        EventMetadata metadata,
        String transactionId,
        DecisionStatus status,
        String reason
) implements DomainEvent {
    public DecisionMadeEvent {
        Objects.requireNonNull(metadata);
        Objects.requireNonNull(transactionId);
        Objects.requireNonNull(status);
    }
}