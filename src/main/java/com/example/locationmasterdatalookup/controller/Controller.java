package com.example.locationmasterdatalookup.controller;

import com.example.locationmasterdatalookup.dto.Location;
import com.example.locationmasterdatalookup.exception.BadRequestException;
import com.example.locationmasterdatalookup.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/location/search")
public class Controller {
	@Autowired
	private LocationService locationService;

	@PostMapping("/loadJson")
	public  Mono<Void> loadJson(@RequestBody String message) {
		return locationService.saveLocation(message);
	}

	@GetMapping("/byName/{name}")
	public Flux<Location> searchByName(@PathVariable("name") String name){
		if(name==null){
			throw new BadRequestException("Name parameter is null");
		}
		return locationService.searchByName(name);
	}

	@GetMapping("/byCode/{code}")
	public Flux<Location> searchByLocationCode(@PathVariable("code") String code){
		return locationService.searchByLocationCode(code);
	}

	@GetMapping("/byCodeType/{type}")
	public Flux<Location> searchByLocationType(@PathVariable("type") String type){
		return locationService.searchByLocationType(type);
	}

	@GetMapping("/byGeoType/{type}")
	public Flux<Location> searchByGeoType(@PathVariable("type") String type){
		return locationService.searchByGeoType(type);
	}
	
}
