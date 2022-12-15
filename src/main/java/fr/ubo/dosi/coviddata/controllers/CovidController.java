package fr.ubo.dosi.coviddata.controllers;

import fr.ubo.dosi.coviddata.Entities.CountryData;
import fr.ubo.dosi.coviddata.Entities.CountryDataByDate;
import fr.ubo.dosi.coviddata.services.CovidService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> oneCountryData(@RequestParam String countryName) {
        countryName=countryName.substring(0, 1).toUpperCase() + countryName.substring(1); //capitalize countryname (maroc => Maroc)
        List<CountryDataByDate> countryDataByDates = covidService.oneCountryData(countryName);
        if (! countryDataByDates.isEmpty())
            return ResponseEntity.status(HttpStatus.FOUND).body(countryDataByDates);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The data is not found on the server");
    }

    @GetMapping("oneCountryDataWithDate")
    public ResponseEntity<Object> oneCountryDataWithDate(@RequestParam String countryName, @RequestParam String date) {
        countryName=countryName.substring(0, 1).toUpperCase() + countryName.substring(1);
        CountryData countryData;
        try{
            countryData=covidService.oneCountryDataWithDate(countryName, LocalDate.parse(date));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FOUND).body("wrong date format, date must be like "+LocalDate.now());
        }
        if(countryData==null)
            return ResponseEntity.status(HttpStatus.FOUND).body("country doesn't exist or date not reached yet");
        return ResponseEntity.ok(countryData);
    }

    @GetMapping("todayCountryData")
    public ResponseEntity<Object>  todayCountryData(@RequestParam String countryName) {
        countryName=countryName.substring(0, 1).toUpperCase() + countryName.substring(1);//capitalize

        CountryData countryData=covidService.oneCountryDataWithDate(countryName, LocalDate.now().minusDays(1));

        if(countryData==null)
            return ResponseEntity.status(HttpStatus.FOUND).body("country doesn't exist");
        return ResponseEntity.ok(countryData);
    }
}
