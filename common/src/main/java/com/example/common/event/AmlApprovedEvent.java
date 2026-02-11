package com.example.common.event;

import java.util.Objects;

public record AmlApprovedEvent(EventMetadata metadata, String transactionId) implements DomainEvent  {
    public AmlApprovedEvent {
        Objects.requireNonNull(metadata);
        Objects.requireNonNull(transactionId, "transactionId must not be null");
    }
}

