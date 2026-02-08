package com.example.ingest.api;

import com.example.ingest.kafka.TransactionEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionEventProducer producer;

    @PostMapping
    public ResponseEntity<Void> ingest(@RequestBody TransactionRequest request) {

        String correlationId = UUID.randomUUID().toString();

        producer.publish(request, correlationId);

        return ResponseEntity.accepted().build();
    }
}
