package com.example.locationmasterdatalookup.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity(name = "location")
@Table
public class Location {

    @Id
    private UUID id;

    @Column
    private String data;
}
