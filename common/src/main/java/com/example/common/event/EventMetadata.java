package com.example.common.event;

import java.time.Instant;
import java.util.UUID;

public record EventMetadata(
        String eventId,
        String correlationId,
        Instant occurredAt
) {

    public EventMetadata {
        if (eventId == null) {
            eventId = UUID.randomUUID().toString();
        }
        if (occurredAt == null) {
            occurredAt = Instant.now();
        }
    }

    public static EventMetadata create(String correlationId) {
        return new EventMetadata(
                UUID.randomUUID().toString(),
                correlationId,
                Instant.now()
        );
    }
}
