package fr.nocturlab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nocturlab.model.Parking;
import fr.nocturlab.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private static final String url_parking = "https://opendata.larochelle.fr/webservice/?service=getData&key=WrY71ysb6kBrpTv7&db=stationnement&table=disponibilite_parking&format=json";

    @Autowired
    private ParkingRepository parkingRepository;

    private RestTemplate restTemplate;

    public ParkingController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/findAll")
    public Iterable<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Scheduled(fixedRate = 100000)
    /**
     * Génère et met à jour des parkings dans la base de données
     */
    public void update() {

        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<MultiValueMap<String, String>> httpEntity =
                new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);


        ResponseEntity<String> response = null;

        try {

            response = this.restTemplate.exchange(url_parking, HttpMethod.GET ,httpEntity, String.class);
        } catch (Exception e) {
            //TODO: handle exception
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String data = objectMapper.readTree(response.getBody()).at("/opendata/answer/data").toString();

            System.out.println(data);

            List<Parking> parkings = Arrays.asList(objectMapper.readValue(data, Parking[].class));

            parkingRepository.saveAll(parkings);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
