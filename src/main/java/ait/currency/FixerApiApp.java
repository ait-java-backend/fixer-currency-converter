package ait.currency;

import ait.currency.dto.FixerResponseDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Scanner;

public class FixerApiApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter currency FROM (e.g., USD): ");
        String from = scanner.nextLine().toUpperCase();

        System.out.println("Enter currency TO (e.g., EUR): ");
        String to = scanner.nextLine().toUpperCase();

        System.out.println("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        String accessKey = "5e77c846adbcdf7eaa614b5b864670ae";
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://data.fixer.io/api/latest")
                .queryParam("access_key", accessKey)
                .queryParam("symbols", from + "," + to);

        URI url = builder.build().toUri();

        RequestEntity<Void> request = new RequestEntity<>(HttpMethod.GET, url);
        ResponseEntity<FixerResponseDto> response = restTemplate.exchange(request, FixerResponseDto.class);

        FixerResponseDto fixer = response.getBody();

        Map<String, Double> rates = fixer.getRates();

        double rateFrom = rates.get(from);
        double rateTo = rates.get(to);
        double result = amount * (rateTo / rateFrom);

        System.out.printf("%.2f %s = %.2f %s%n", amount, from, result, to);
    }
}
