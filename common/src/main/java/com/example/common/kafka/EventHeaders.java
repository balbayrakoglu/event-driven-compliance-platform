package com.example.common.kafka;

/**
 * Standard Kafka header names used across the platform.
 *
 * <p>
 * Headers carry technical metadata.
 * Business data must never be placed in headers.
 * </p>
 */
public final class EventHeaders {

    private EventHeaders() {
        // utility class
    }

    /**
     * Correlates all events belonging to the same business flow.
     */
    public static final String CORRELATION_ID = "correlation-id";

    /**
     * Identifies the event that directly caused this event.
     * Useful for debugging and tracing event chains.
     */
    public static final String CAUSATION_ID = "causation-id";

    /**
     * Fully qualified event type name.
     * Example: com.example.common.event.TransactionReceivedEvent
     */
    public static final String EVENT_TYPE = "event-type";

    /**
     * Schema version of the event payload.
     * Used for backward-compatible evolution.
     */
    public static final String SCHEMA_VERSION = "schema-version";

    /**
     * Timestamp (epoch millis) when the event was produced.
     */
    public static final String PRODUCED_AT = "produced-at";
}
