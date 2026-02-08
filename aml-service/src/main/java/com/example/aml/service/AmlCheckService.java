package com.example.aml.service;

import com.example.common.event.TransactionReceivedEvent;
import org.springframework.stereotype.Service;

@Service
public class AmlCheckService {
    public void evaluate(TransactionReceivedEvent event) {
        // Simulated AML check
        if (event.getAmount().longValue() > 1_000_000) {
            throw new IllegalStateException(
                    "AML threshold exceeded for transaction " + event.getTransactionId()
            );
        }
    }
}
