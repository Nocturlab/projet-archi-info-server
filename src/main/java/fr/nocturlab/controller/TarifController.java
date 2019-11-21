package fr.nocturlab.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nocturlab.model.Tarif;
import fr.nocturlab.repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class TarifController {

    private static final String URL = "https://opendata.larochelle.fr/webservice/?service=getData&key=WrY71ysb6kBrpTv7&db=stationnement&table=sta_parking_tarif_synthese&format=json&mode=respecttypage&unquoted[]=donnees";

    @Autowired
    private TarifRepository repository;
    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    public TarifController() {
        restTemplate = new RestTemplate();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    @RequestMapping("/tarifs/findAll")
    public Iterable<Tarif> findAll() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 100000)
    public void update() throws IOException {
        // GET the data from La Rochelle OpenData API
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

        // Parse the json
        String data = mapper.readTree(response.getBody()).at("/opendata/answer/data/0/donnees/grilletarif/parking_mode_tarification/row").toString();
        List<Tarif> tarifs = Arrays.asList(mapper.readValue(data, Tarif[].class));

        // Save in database
        repository.saveAll(tarifs);
    }
}
