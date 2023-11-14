package com.example.locationmasterdatalookup.service;

import com.example.locationmasterdatalookup.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Mono<Void> saveLocation(String message) {
        return this.locationRepository.saveValues(UUID.randomUUID(), message);
    }

}
