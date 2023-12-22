package com.example.locationmasterdatalookup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
@Service
public class ReactiveConsumerService{

    @Autowired
    private ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;

    @Autowired
    private LocationService locationService;

    public ReactiveConsumerService(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

    @EventListener(ApplicationStartedEvent.class)
    protected Flux<Void> consumeLocationData() {
        return reactiveKafkaConsumerTemplate
                .receive()
                .delayElements(Duration.ofSeconds(2L)) // BACKPRESSURE
                .doOnNext(consumerRecord -> log.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .flatMap(consumerRecord -> locationService.saveLocation(consumerRecord.value())
                        .doOnSuccess(location -> {
                            log.info("successfully consumed {}", consumerRecord.value());
                            consumerRecord.receiverOffset().acknowledge();
                        })
                        .doOnError(throwable -> log.error("DB error while consuming : {}", throwable.getMessage()))
                )
                .doOnError(throwable -> log.error("Kafka error while consuming : {}", throwable.getMessage()))
                .doFinally(t->log.info("In Finally"));
    }

}
