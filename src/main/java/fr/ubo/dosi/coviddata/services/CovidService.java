package fr.ubo.dosi.coviddata.services;

import fr.ubo.dosi.coviddata.Entities.CountryData;
import fr.ubo.dosi.coviddata.Entities.CountryDataByDate;

import java.time.LocalDate;
import java.util.List;

public interface CovidService {

    public List<CountryDataByDate> oneCountryData(String countryName);

    public CountryData oneCountryDataWithDate(String countryName, LocalDate date);
}
