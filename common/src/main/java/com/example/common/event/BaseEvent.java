package com.example.common.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Base class for all domain events.
 *
 * <p>
 * An event represents an immutable business fact that already happened.
 * Events must be safe to replay and must not contain transient state.
 * </p>
 */


public abstract class BaseEvent {
    /**
     * Unique identifier of this event instance.
     * Used for idempotency and deduplication.
     */
    private final String eventId;

    /**
     * Correlation identifier used to trace a business flow
     * across multiple services and events.
     */
    private final String correlationId;

    /**
     * Timestamp indicating when the event occurred.
     */
    private final Instant occurredAt;


    protected BaseEvent(String correlationId) {
        this.eventId = UUID.randomUUID().toString();
        this.correlationId = correlationId;
        this.occurredAt = Instant.now();
    }

    public String getEventId() {
        return eventId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

}
