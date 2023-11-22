package com.example.locationmasterdatalookup.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "location")
@Table
@Getter
@Setter
public class Location {

    @Id
    private UUID id;

    @Column
    private String data;
}
