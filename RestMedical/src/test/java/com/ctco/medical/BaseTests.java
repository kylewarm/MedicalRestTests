package com.ctco.medical;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public abstract class BaseTests {

    static RequestSpecification requestSpecification;

    @BeforeAll
    public static void init() throws IOException {
        Properties configurationProperties = new Properties();
        configurationProperties.load(ClassLoader.class.getResourceAsStream("/configuration.properties"));

        String baseURL = configurationProperties.getProperty("baseURL");
        String proxyHost = configurationProperties.getProperty("proxyHost");
        int proxyPort = Integer.parseInt(configurationProperties.getProperty("proxyPort"));

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy(proxyHost, proxyPort)
                .setBaseUri(baseURL)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    String getJsonFromFile(String fileName) {
        return new Scanner(getClass().getResourceAsStream(fileName), "UTF-8").useDelimiter("\\A").next();
    }

}
