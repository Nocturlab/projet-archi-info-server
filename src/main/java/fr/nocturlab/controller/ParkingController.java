package fr.nocturlab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import fr.nocturlab.model.Parking;
import fr.nocturlab.repository.ParkingRepository;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private static final String url_parking = "https://opendata.larochelle.fr/webservice/?service=getData&key=WrY71ysb6kBrpTv7&db=stationnement&table=disponibilite_parking";

    @Autowired
    private ParkingRepository parkingRepository;

    private RestTemplate restTemplate;

    public ParkingController() {
        this.restTemplate = new RestTemplate();
    }



    @Scheduled(fixedRate = 300)
    /**
     * Génère et met à jour des parkings dans la base de données
     */
    public void update(){

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


    @GetMapping("/findNearestParking")
    /**
     *  Retourne les parkings pas trop loin
     * @param lat latitude de l'utilisateur
     * @param lng longitude de l'utilisateur
     * @param dist distance en mètre maximale des parkings retournés
     * @return Une liste de parkings pas trop loin
     */
    List<Parking> findNearestParking(@RequestParam(name = "lat", required = true) Double lat, @RequestParam(name = "lng", required = true) Double lng, @RequestParam(name = "dist", required = true) int dist){
        List<Parking> res = new ArrayList<>();

        Iterable<Parking> parkings = parkingRepository.findAll();
 
        Iterator<Parking> parkingsIterator = parkings.iterator();

        while(parkingsIterator.hasNext()){
            Parking parking = parkingsIterator.next();
            if( distance(lat, lng, parking.getLat(), parking.getLng()) < dist){
                res.add(parking);
            }
        }

        return res;
    }

    private float distance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    
}
