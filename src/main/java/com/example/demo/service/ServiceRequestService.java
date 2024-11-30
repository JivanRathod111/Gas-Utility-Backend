package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ServiceRequest;
import com.example.demo.repository.ServiceRequestRepository;

@Service
public class ServiceRequestService {
    @Autowired
    private ServiceRequestRepository  repository;

    public ServiceRequest createRequest(ServiceRequest request) {
        request.setSubmittedAt(LocalDateTime.now());
        request.setStatus("Pending");
        return repository.save(request);
    }

    public List<ServiceRequest> getAllRequests() {
        return repository.findAll();
    }

    public Optional<ServiceRequest> getRequestById(Long id) {
        return repository.findById(id);
    }

    public void updateStatus(Long id, String status) {
        ServiceRequest request = repository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        if (status.equals("Resolved")) {
            request.setResolvedAt(LocalDateTime.now());
        }
        repository.save(request);
    }
}
