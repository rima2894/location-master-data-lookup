package com.example.locationmasterdatalookup.repository;

import com.example.locationmasterdatalookup.dto.Location;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface LocationRepository extends R2dbcRepository<Location, Integer>{

    @Query("INSERT INTO location (id, data) VALUES (:id, CAST(:data AS jsonb))")
    Mono<Void>  saveValues(@Param("id") UUID id, @Param("data") String data);

    @Query(value = "SELECT * FROM location WHERE data->>'name' LIKE '%'||$1||'%'")
    Flux<Location> findByName(String name);

    @Query(value = "SELECT * FROM location t,jsonb_array_elements(data->'alternateCodes') alternateCodes " +
            "WHERE alternateCodes->>'code' = $1")
    Flux<Location> findByLocationCode(String code);

    @Query(value = "SELECT * FROM location t,jsonb_array_elements(data->'alternateCodes') alternateCodes " +
            "WHERE alternateCodes->>'codeType' = $1")
    Flux<Location> findByLocationType(String type);

    @Query(value = "SELECT * FROM location WHERE data->>'geoType' = $1")
    Flux<Location> findByGeoType(String geotype);

}
