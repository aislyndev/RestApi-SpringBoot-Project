package com.projerestapi.projerestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projerestapi.projerestapi.exception.CountryAlreadyExistsException;
import com.projerestapi.projerestapi.exception.CountryNotFoundException;

import com.projerestapi.projerestapi.model.Country;
import com.projerestapi.projerestapi.service.CountryService;

import lombok.AllArgsConstructor;





@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {
	
 
private final CountryService countryService;

	

	
	
	
	@GetMapping
	public ResponseEntity<List<Country>>getCountries(@RequestParam(required = false)String name){
		return new ResponseEntity<>(countryService.getCountries(name), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Country> getCountry(@PathVariable String id){
		return new ResponseEntity<>(getCountryById(id),HttpStatus.OK);
	
       }
	
	
	@PostMapping
	public  ResponseEntity<Country> createCountry(@RequestBody Country newCountry)
	{
		return new ResponseEntity<>(countryService.createCountry(newCountry),HttpStatus.CREATED);
		
	}
	@PutMapping("/{id}")
        public ResponseEntity<Void> getCountry(@PathVariable String id,@RequestBody Country newCountry)
       {
		countryService.updateCountry(id, newCountry);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCountry(@PathVariable String id){
		countryService.deleteCountry(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	private Country getCountryById(String id) {
		return countryService.getCountryById(id);
		
	}
	
	@ExceptionHandler(CountryNotFoundException.class)
	public ResponseEntity<String> handleCountryNotFoundException(CountryNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
	
	@ExceptionHandler(CountryAlreadyExistsException.class)
	public ResponseEntity<String> handleCountryAlreadyExistsException(CountryAlreadyExistsException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
	}
	
}	
	
