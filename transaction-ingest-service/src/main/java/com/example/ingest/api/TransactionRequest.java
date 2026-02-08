package com.example.ingest.api;

import java.math.BigDecimal;
/**
 * Incoming HTTP request payload.
 *
 * This is NOT a Kafka event.
 * It represents external input and may change independently.
 */
public record TransactionRequest(String transactionId,
                                 String accountId,
                                 BigDecimal amount,
                                 String currency) {
}
