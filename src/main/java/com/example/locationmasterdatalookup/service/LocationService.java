package com.example.locationmasterdatalookup.service;

import com.example.locationmasterdatalookup.dto.Location;
import com.example.locationmasterdatalookup.exception.BadRequestException;
import com.example.locationmasterdatalookup.repository.LocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    //@Autowired

    public Mono<Void> saveLocation(String message) {
        return this.locationRepository.saveValues(UUID.randomUUID(), message)
                .doOnSuccess(e -> log.info("Success in saveLocation"))
                .doOnError(e -> log.info("Error in saveLocation {}", e.getMessage()));
    }

    public Flux<Location> searchByName(String name){
        return locationRepository.findByName(name)
                .doOnError(data->{throw new BadRequestException("BadPayload in Name");});
    }

    public Flux<Location> searchByLocationCode(String code){
        return locationRepository.findByLocationCode(code)
                .doOnError(data->{throw new BadRequestException("BadPayload in locationcode");});
    }

    public Flux<Location> searchByLocationType(String type){
        return locationRepository.findByLocationType(type)
                .doOnError(data->{throw new BadRequestException("BadPayload in locationType");});
    }

    public Flux<Location> searchByGeoType(String type){
        return locationRepository.findByGeoType(type)
                .doOnError(data->{throw new BadRequestException("BadPayload in GeoType");});
    }
}
