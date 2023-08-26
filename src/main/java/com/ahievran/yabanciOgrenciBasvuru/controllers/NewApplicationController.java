package com.ahievran.yabanciOgrenciBasvuru.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiBasvuruService;
import com.ahievran.yabanciOgrenciBasvuru.business.responses.SehirlerResponse;

@Controller
@RestController
public class NewApplicationController {
	@Autowired
	private KisiBasvuruService basvuruService;
    @GetMapping("/loadCities")
    @ResponseBody
    public List<SehirlerResponse> loadCitiesByCountry(@RequestParam int countryId) {

        List<SehirlerResponse> cities  =  basvuruService.getSehirByUlke(countryId);
        System.out.println(cities.get(0).getIngilizceAd());
        return cities;
    }
	
}
