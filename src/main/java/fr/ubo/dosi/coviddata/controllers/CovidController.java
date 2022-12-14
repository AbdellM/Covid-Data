package fr.ubo.dosi.git remote add origin https://github.com/AbdellM/Covid-Data.git.controllers;

import fr.ubo.dosi.coviddata.Entities.CountryData;
import fr.ubo.dosi.coviddata.Entities.CountryDataByDate;
import fr.ubo.dosi.coviddata.services.CovidService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController @AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CovidController {
    private CovidService covidService;

    @GetMapping("oneCountryData")
    public List<CountryDataByDate> oneCountryData(@RequestParam String countryName) {
        return covidService.oneCountryData(countryName);
    }

    @GetMapping("oneCountryDataWithDate")
    public CountryData oneCountryDataWithDate(@RequestParam String countryName,@RequestParam String date) {
        return covidService.oneCountryDataWithDate(countryName, LocalDate.parse(date));
    }

    @GetMapping("todayCountryData")
    public CountryData todayCountryData(@RequestParam String countryName) {
        return covidService.oneCountryDataWithDate(countryName, LocalDate.now().minusDays(1));
    }
}
