package fr.ubo.dosi.coviddata.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CountryDataByDate extends CountryData {
    private Double deathRate;
    private Double recoveredRate;
    private Double infectionRate;

    public CountryDataByDate(LocalDate date, String countryName, Long infections, Long deaths, Long recovered, Double deathRate, Double recoveredRate, Double infectionRate) {
        super(date, countryName, infections, deaths, recovered);
        this.deathRate = deathRate;
        this.recoveredRate = recoveredRate;
        this.infectionRate = infectionRate;
    }
}
