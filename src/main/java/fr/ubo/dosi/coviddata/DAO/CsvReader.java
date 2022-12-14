package fr.ubo.dosi.coviddata.DAO;

import fr.ubo.dosi.coviddata.Entities.CountryDataByDate;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CsvReader {
    public static List<CountryDataByDate> mappingData() throws Exception {
        log.info("DAO : loading all csv data into static list");

        downloadingFile();
        File file = new File("./src/main/resources/data.csv");
        try (FileInputStream fis = new FileInputStream(file)) {
            List<CountryDataByDate> countries = Files.lines(file.toPath())
                    .skip(9)
                    .map(line -> line.split(";"))
                    .map(CsvReader::parsingData)
                    .collect(Collectors.toList());
            return countries;
        } catch (IOException e) {
            throw new Exception("Error while reading file", e);
        }
    }
    public static CountryDataByDate parsingData(String[] str)
    {
        LocalDate date = LocalDate.parse(str[0]);
        String countryName = str[1];
        Long infections = Long.parseLong(str[2]);
        Long deaths = Long.parseLong(str[3]);
        Long recovered = Long.parseLong(str[4]);
        Double deathRate = Double.parseDouble(str[5]);
        Double recoveredRate = Double.parseDouble(str[6]);
        Double infectionRate = Double.parseDouble(str[7]);

        return new CountryDataByDate(date, countryName, infections, deaths, recovered, deathRate, recoveredRate, infectionRate);
    }
    public static void downloadingFile() {
        log.info("DAO : download csv file");

        String FILE_URL = "https://coronavirus.politologue.com/data/coronavirus/coronacsv.aspx?format=csv&t=pays";
        String FILE_NAME = "./src/main/resources/data.csv";
        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}