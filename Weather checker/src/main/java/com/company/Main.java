package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static String createCityWeatherUrl(String city) {
        return "http://api.openweathermap.org/data/2.5/weather?q=" + city.trim().toLowerCase() + "&APPID=49fde737e9c175f7e451dde31668dc9c&units=metric";
    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        Scanner in  = new Scanner(System.in);
        System.out.println("Enter city name: ");
        String city = in.nextLine();
        CloseableHttpResponse response = client.execute(new HttpGet(createCityWeatherUrl(city)));
        String responseString = EntityUtils.toString(response.getEntity());
//        System.out.println(responseString);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(responseString, Map.class);
        System.out.println("Current temperature is: " + ((Map<String, Object>) data.get("main")).get("temp") );

    }
}
