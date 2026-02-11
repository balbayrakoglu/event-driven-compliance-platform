package com.example.common.event;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionReceivedEvent(
        EventMetadata metadata,
        String transactionId,
        String accountId,
        BigDecimal amount,
        String currency
) implements DomainEvent {

    public TransactionReceivedEvent {
        Objects.requireNonNull(metadata, "metadata must not be null");
        Objects.requireNonNull(transactionId, "transactionId must not be null");
        Objects.requireNonNull(accountId, "accountId must not be null");
        Objects.requireNonNull(amount, "amount must not be null");
        Objects.requireNonNull(currency, "currency must not be null");
    }
}
