package services;


import fr.ubo.dosi.coviddata.Entities.CountryData;
import fr.ubo.dosi.coviddata.Entities.CountryDataByDate;
import fr.ubo.dosi.coviddata.services.CovidService;
import fr.ubo.dosi.coviddata.services.CovidServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class CovidServiceTest {

    CovidService covidService=new CovidServiceImpl();

    CountryDataByDate france =new CountryDataByDate(
            LocalDate.now().minusDays(1),
            "France",
            37596953L,
            156446L,
            0L,
            0.42,
            (double) 0,
            99.58
    );

    CountryData francewithdate =new CountryData(
            LocalDate.now().minusDays(1),
            "France",
            69866L,
            109L,
            0L
    );

    @Test
    public void testGetCountryByName()
    {
        List<CountryDataByDate> countries = covidService.oneCountryData("France");
        Assert.isTrue(countries.contains(france), "Country is not equal");
    }

    @Test
    public void testGetCountryByWrongName()
    {
        List<CountryDataByDate> countries = covidService.oneCountryData("ffff");
        Assert.isTrue(!countries.contains(france), "Country is not equal");
    }

    @Test
    public void testGetCountryByNameAndDate()
    {
        CountryData country = covidService.oneCountryDataWithDate("France",LocalDate.now().minusDays(1));
        Assert.isTrue(francewithdate.equals(country), "Country is not equal");
    }

    @Test
    public void testGetCountryByNameAndDateWrong()
    {
        CountryData country = covidService.oneCountryDataWithDate("fff",LocalDate.now().minusDays(1));
        Assert.isTrue(!francewithdate.equals(country), "Country is not equal");
    }
}
