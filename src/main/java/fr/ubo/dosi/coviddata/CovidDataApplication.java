package fr.ubo.dosi.coviddata;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@SpringBootApplication
public class CovidDataApplication {


    public static void main(String[] args) {SpringApplication.run(CovidDataApplication.class, args);}
}
