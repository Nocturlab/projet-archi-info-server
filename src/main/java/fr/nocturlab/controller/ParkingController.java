package fr.nocturlab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nocturlab.model.Parking;
import fr.nocturlab.model.Stationnement;
import fr.nocturlab.repository.ParkingRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private static final String url_base_api = "https://opendata.larochelle.fr/";
    private static final String url_parking_temps_reel = url_base_api+"webservice/?service=getData&key=WrY71ysb6kBrpTv7&db=stationnement&table=disponibilite_parking&format=json";

    private  static final String url_parking = url_base_api+"/webservice/?service=getData&key=WrY71ysb6kBrpTv7&db&db=stationnement&table=sta_parking&format=json";

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private StationnementRepository stationnementRepository;

    private RestTemplate restTemplate;

    public ParkingController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/findAll")
    public Iterable<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @GetMapping("/disponibles")
    public Iterable<Parking> parkings() {
        return parkingRepository.findByPlacesDisponiblesIsGreaterThan(0);
    }


    @Scheduled(fixedRate = 1000000000)
    public void generate() {
        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<MultiValueMap<String, String>> httpEntity =
                new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);


        ResponseEntity<String> response = null;

        try {

            response = this.restTemplate.exchange(url_parking, HttpMethod.GET ,httpEntity, String.class);
        } catch (Exception e) {
            //TODO: handle exception
        }

//        System.out.println(response.toString());
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String data = objectMapper.readTree(response.getBody()).at("/opendata/answer/data").toString();

            System.out.println(data);

            List<Stationnement> parkings = Arrays.asList(objectMapper.readValue(data, Stationnement[].class));

            stationnementRepository.saveAll(parkings);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retourne à la fois les parkings en temps réel et les stationnements
     */
    @GetMapping("/all")
    public String getAllParkings()
    {
        JSONArray array = new JSONArray();

        Iterable<Parking> parkings = parkingRepository.findAll();

        Iterable<Stationnement> stationnements = stationnementRepository.findAll();

        System.out.println("getallparkings");


        Iterator<Parking> iteratorParking = parkings.iterator();
        Iterator<Stationnement> iteratorStationnement = stationnements.iterator();

        while(iteratorStationnement.hasNext()){

            Stationnement s = iteratorStationnement.next();

            JSONObject object = new JSONObject();

            try {
                object.put("parking_id", s.getId());
                object.put("parking_x", s.getParking_x());
                object.put("parking_y", s.getParking_y());
                object.put("parking_libelle", s.getLibelle());
                object.put("parking_nom", s.getNom());
                object.put("parking_nb_places", s.getNb_places());
                object.put("parking_nb_place_reel", "NULL");
                object.put("parking_", s.getId());
                object.put("parking_tarification", s.getTarification());
                object.put("parking_type", s.getType());

                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(object.toString());
        }

        while(iteratorParking.hasNext()){

            Parking s = iteratorParking.next();

            JSONObject object = new JSONObject();

            try {
                object.put("parking_id", s.getId());
                object.put("parking_x", s.getX());
                object.put("parking_y", s.getY());
                object.put("parking_libelle", s.getLibelle());
                object.put("parking_nom", s.getLibelle());
                object.put("parking_nb_places", s.getNombresPlaces());
                object.put("parking_nb_place_reel", s.getPlacesDisponibles());
                object.put("parking_", s.getId());
                object.put("parking_tarification", "NULL");
                object.put("parking_type", "NULL");

                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(object.toString());
        }


        return array.toString();
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

            response = this.restTemplate.exchange(url_parking_temps_reel, HttpMethod.GET ,httpEntity, String.class);
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
