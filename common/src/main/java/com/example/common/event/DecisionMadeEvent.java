package com.example.common.event;

import java.util.Objects;

public final class DecisionMadeEvent extends BaseEvent {

    private final String transactionId;
    private final DecisionStatus status;
    private final String reason;

    public DecisionMadeEvent(
            String correlationId,
            String transactionId,
            DecisionStatus status,
            String reason
    ) {
        super(correlationId);

        this.transactionId = Objects.requireNonNull(transactionId);
        this.status = Objects.requireNonNull(status);
        this.reason = reason;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public DecisionStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}
