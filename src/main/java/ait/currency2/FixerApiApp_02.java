package ait.currency2;

import ait.currency2.dto.ConvertResponseDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Scanner;

public class FixerApiApp_02 {
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
                .fromHttpUrl("http://data.fixer.io/api/convert")
                .queryParam("access_key", accessKey)
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("amount", amount);

        URI url = builder.build().toUri();

        RequestEntity<Void> request = new RequestEntity<>(HttpMethod.GET, url);
        ResponseEntity<ConvertResponseDto> response = restTemplate.exchange(request, ConvertResponseDto.class);

        ConvertResponseDto data = response.getBody();

        System.out.printf("%.2f %s = %.2f %s%n",
                data.getQuery().getAmount(),
                data.getQuery().getFrom(),
                data.getResult(),
                data.getQuery().getTo());
    }
}
