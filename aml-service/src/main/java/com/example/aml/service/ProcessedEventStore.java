package com.example.aml.service;

import com.example.aml.persistence.entity.ProcessedEvent;
import com.example.aml.persistence.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProcessedEventStore {

    private final ProcessedEventRepository repository;


    @Transactional
    public void markProcessed(String eventId) {
        repository.save(new ProcessedEvent(eventId));
    }

    public boolean isDuplicate(Throwable ex) {
        return ex instanceof DataIntegrityViolationException;
    }
}
