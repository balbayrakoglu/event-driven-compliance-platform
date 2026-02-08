package com.example.common.event;


import java.math.BigDecimal;
import java.util.Objects;

/**
 * Event published when a transaction is received by the platform.
 *
 * <p>
 * This event represents a business fact and is immutable.
 * It can be safely replayed and consumed by multiple downstream services.
 * </p>
 */

public final class TransactionReceivedEvent extends BaseEvent {
    /**
     * Unique identifier of the transaction in the source system.
     */
    private final String transactionId;

    /**
     * Identifier of the account initiating the transaction.
     */
    private final String accountId;
    /**
     * Monetary amount of the transaction.
     */
    private final BigDecimal amount;

    /**
     * ISO 4217 currency code (e.g. EUR, USD).
     */
    private final String currency;

    public TransactionReceivedEvent(String correlationId,
                                    String transactionId,
                                    BigDecimal amount,
                                    String currency,
                                    String accountId) {
        super(correlationId);

        this.transactionId = Objects.requireNonNull(transactionId, "transactionId must not be null"); // String is immutable and safe to use as-is
        this.amount = Objects.requireNonNull(amount, "amount must not be null"); // BigDecimal is immutable and safe to use as-is
        this.currency = Objects.requireNonNull(currency, "currency must not be null"); // String is immutable and safe to use as-is
        this.accountId = Objects.requireNonNull(accountId, "accountId must not be null"); // String is immutable and safe to use as-is
        // Invalid events should never enter the system, so we throw an exception if any required field is null.
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
