package com.example.common.event;

public class AmlApprovedEvent extends BaseEvent {

    private final String transactionId;

    public AmlApprovedEvent(
            String correlationId,
            String transactionId
    ) {
        super(correlationId);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}

