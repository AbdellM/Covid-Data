package fr.ubo.dosi.coviddata.services;

import fr.ubo.dosi.coviddata.DAO.CsvReader;
import fr.ubo.dosi.coviddata.Entities.CountryData;
import fr.ubo.dosi.coviddata.Entities.CountryDataByDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service @Slf4j
public class CovidServiceImpl implements CovidService {
    static private List<CountryDataByDate> countryList;

    static {
        try {
            countryList = CsvReader.mappingData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CountryDataByDate> oneCountryData(String countryName) {
        log.info("SERVICE : Get covid data for "+countryName);


        return countryList.stream()
                .filter(
                        country -> country.getCountryName()
                                .equals(countryName)
                )
                .collect(
                        Collectors.toList()
                );
    }

    @Override
    public CountryData oneCountryDataWithDate(String countryName, LocalDate date) {
        CountryData currentDay;
        try {
        currentDay = getOneRowByCountryAndDate(countryName, date);
        CountryData dayBefore = getOneRowByCountryAndDate(countryName, date.minusDays(1));
        currentDay.setInfections(currentDay.getInfections() - dayBefore.getInfections());
        currentDay.setDeaths(currentDay.getDeaths() - dayBefore.getDeaths());
        currentDay.setRecovered(currentDay.getRecovered() - dayBefore.getRecovered());
        }
        catch (Exception e){
            return null;
        }
        return currentDay;
    }

    private CountryData getOneRowByCountryAndDate(String countryName, LocalDate date) {
        AtomicInteger count = new AtomicInteger();
        Stream<CountryDataByDate> stream = countryList
                .stream()
                .filter(country -> country.getCountryName().equals(countryName) && country.getDate().isEqual(date))
                .peek(country -> count.getAndIncrement());
        return new CountryData(stream.findFirst().get());
    }
}
