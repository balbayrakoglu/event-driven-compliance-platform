package com.example.common.kafka;

public final class TopicConstants {

    private TopicConstants() {
        // utility class
    }

    public static final String TRANSACTION_EVENTS_V1 = "transaction.events.v1";
    public static final String COMPLIANCE_EVENTS_V1 = "compliance.events.v1";
    public static final String DECISION_EVENTS_V1 = "decision.events.v1";

    // =====================================================
    // Retry topics (exponential backoff)
    // =====================================================

    public static final String TRANSACTION_EVENTS_RETRY_5S =
            "transaction.events.retry.5s";

    public static final String TRANSACTION_EVENTS_RETRY_30S =
            "transaction.events.retry.30s";

    public static final String TRANSACTION_EVENTS_RETRY_5M =
            "transaction.events.retry.5m";


    // =====================================================
    // Dead Letter Topics (terminal failures)
    // =====================================================

    public static final String TRANSACTION_EVENTS_DLT =
            "transaction.events.dlt";

    public static final String COMPLIANCE_EVENTS_DLT =
            "compliance.events.dlt";
}
