package com.example.common.event;

public class AmlDecisionCompensatedEvent extends BaseEvent {

    private final String transactionId;
    private final String previousStatus;
    private final String reason;

    public AmlDecisionCompensatedEvent(
            String correlationId,
            String transactionId,
            String previousStatus,
            String reason
    ) {
        super(correlationId);
        this.transactionId = transactionId;
        this.previousStatus = previousStatus;
        this.reason = reason;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public String getReason() {
        return reason;
    }
}
