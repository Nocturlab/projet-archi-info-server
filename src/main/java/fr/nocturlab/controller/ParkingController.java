package fr.nocturlab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nocturlab.model.Parking;
import fr.nocturlab.repository.ParkingRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ParkingController {

    private static final String url_parking = "https://opendata.larochelle.fr/webservice/?service=getData&key=WrY71ysb6kBrpTv7&db=stationnement&table=disponibilite_parking";

    private ParkingRepository parkingRepository;

    private RestTemplate restTemplate;

    public ParkingController(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
        this.restTemplate = new RestTemplate();
    }



    @Scheduled(fixedRate = 300)
    /**
     * Génère et met à jour des parkings dans la base de données
     */
    public void update() {

        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<MultiValueMap<String, String>> httpEntity =
                new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);


        ResponseEntity<String> parkings = null;

        try {
            parkings = this.restTemplate.exchange(url_parking, HttpMethod.GET ,httpEntity, String.class);
        } catch (Exception e) {
            //TODO: handle exception
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Parking> liste = new ArrayList<>();

        try {
            liste = objectMapper.readValue(parkings.getBody(), objectMapper.getTypeFactory().
                    constructCollectionType(List.class, Parking.class));

        } catch (IOException e) {
            e.printStackTrace();
        }


        for(Parking p : liste){

            System.out.println(p.toString());
            this.parkingRepository.save(p);
        }
    }
}
