package com.example.locationmasterdatalookup.repository;

import com.example.locationmasterdatalookup.dto.Location;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface LocationRepository extends R2dbcRepository<Location, Integer>{

    @Query("INSERT INTO location (id, data) VALUES (:value1, :value2)")
    Mono<Void> saveValues(@Param("value1") UUID value1, @Param("value2") String value2);

}
