package com.example.locationmasterdatalookup.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReactiveConsumerServiceTest {

    @Mock
    private ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;
    @Mock
    private LocationService locationService;
    @InjectMocks
    private ReactiveConsumerService locationDataConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsumeLocationData() {
        ConsumerRecord<String, String> testConsumerRecord = new ConsumerRecord<>("test-topic", 0, 0L, "test-key", "test-value");
        when(reactiveKafkaConsumerTemplate.receiveAutoAck()).thenReturn(Flux.just(testConsumerRecord));
        when(locationService.saveLocation("test-value")).thenReturn(Mono.empty());
        Flux<Void> result = locationDataConsumer.consumeLocationData();
        StepVerifier.create(result)
                .verifyComplete();
    }

}