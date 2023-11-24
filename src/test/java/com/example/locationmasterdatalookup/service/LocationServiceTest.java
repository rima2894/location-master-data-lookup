package com.example.locationmasterdatalookup.service;

import com.example.locationmasterdatalookup.dto.Location;
import com.example.locationmasterdatalookup.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveLocation() {
        String testMessage = "Test message";
        when(locationRepository.saveValues(any(UUID.class), any(String.class))).thenReturn(Mono.empty());
        Mono<Void> result = locationService.saveLocation(testMessage);
        StepVerifier.create(result).verifyComplete();
    }

    @Test
    void testSearchByName() {
        String testName = "TestName";
        when(locationRepository.findByName(any(String.class))).thenReturn(Flux.empty());
        Flux<Location> result = locationService.searchByName(testName);
        StepVerifier.create(result).verifyComplete();
    }

    @Test
    void testSearchByLocationCode() {
        String testName = "TestLocationCode";
        when(locationRepository.findByLocationCode(any(String.class))).thenReturn(Flux.empty());
        Flux<Location> result = locationService.searchByLocationCode(testName);
        StepVerifier.create(result).verifyComplete();
    }

    @Test
    void testSearchByLocationType() {
        String testName = "TestLocationType";
        when(locationRepository.findByLocationType(any(String.class))).thenReturn(Flux.empty());
        Flux<Location> result = locationService.searchByLocationType(testName);
        StepVerifier.create(result).verifyComplete();
    }

    @Test
    void testSearchByGeoType() {
        String testName = "TestGeoType";
        when(locationRepository.findByGeoType(any(String.class))).thenReturn(Flux.empty());
        Flux<Location> result = locationService.searchByGeoType(testName);
        StepVerifier.create(result).verifyComplete();
    }
}
