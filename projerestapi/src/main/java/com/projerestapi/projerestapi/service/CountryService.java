package com.projerestapi.projerestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projerestapi.projerestapi.exception.CountryAlreadyExistsException;
import com.projerestapi.projerestapi.model.Country;
import com.projerestapi.projerestapi.repository.CountryRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CountryService {
	
	private final CountryRepository countryRepository;

	public List<Country> getCountries(String name) {
		
		if (name == null) {
			return countryRepository.findAll();
		}else {
			return countryRepository.findAllByName(name);
		}
		
	}

	public Country createCountry(Country newCountry) {
		
		Optional<Country> countryByName = countryRepository.findByName(newCountry.getName());
		if(countryByName.isPresent())
		{
		throw new CountryAlreadyExistsException("Country already exists with name:" +newCountry.getName());
	    
		}
		return countryRepository.save(newCountry);
	}

	public void deleteCountry(String id) {
	    countryRepository.deleteById(id);
		
	}

	public Country getCountryById(String id)
    {
		
		return countryRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Country not found"));
		
	}

	public void updateCountry(String id, Country newCountry) {
		Country oldcountry = getCountryById(id);
		oldcountry.setName(newCountry.getName());
		countryRepository.save(oldcountry);
	}
}
