//https://opendata.larochelle.fr/dataset/stationnement-place-reservee-aux-personnes-a-mobilite-reduite/
package fr.nocturlab.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.nocturlab.model.PlaceMR;
import fr.nocturlab.repository.PlaceMRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PlaceMRController {

    private static final String URL = "https://opendata.larochelle.fr/webservice/?service=getData&key=WrY71ysb6kBrpTv7&db=stationnement&db=stationnement&table=sta_place_reservee&format=json";

    @Autowired
    private PlaceMRRepository repository;
    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    public PlaceMRController() {
        restTemplate = new RestTemplate();
        mapper = new ObjectMapper();
    }

    @RequestMapping("/PlaceMR/findAll")
    public Iterable<PlaceMR> findAll() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 100000)
    public void update() throws IOException {
        // GET the data from La Rochelle OpenData API
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

        // Parse the json
        String data = mapper.readTree(response.getBody()).at("/opendata/answer/data").toString();
        List<PlaceMR> PlaceMRs = Arrays.asList(mapper.readValue(data, PlaceMR[].class));

        // Save in database
        repository.saveAll(PlaceMRs);
    }
}
