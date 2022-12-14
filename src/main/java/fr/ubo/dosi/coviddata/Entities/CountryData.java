package fr.ubo.dosi.coviddata.Entities;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class CountryData {
    protected LocalDate date;
    protected String countryName;
    protected Long infections;
    protected Long deaths;
    protected Long recovered;


    public CountryData(CountryDataByDate countryDataByDate) {
        this.date = countryDataByDate.getDate();
        this.countryName = countryDataByDate.getCountryName();
        this.infections = countryDataByDate.getInfections();
        this.deaths = countryDataByDate.getDeaths();
        this.recovered = countryDataByDate.getRecovered();
    }
}
