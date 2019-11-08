package fr.nocturlab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nocturlab.model.Horodateur;
import fr.nocturlab.repository.HorodateurRepository;
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
public class HorodateurController {

    private static final String URL = "https://opendata.larochelle.fr/webservice/?service=getData&key=WrY71ysb6kBrpTv7&db=stationnement&db=stationnement&table=sta_horodateur&format=json";

    @Autowired
    private HorodateurRepository repository;
    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    public HorodateurController() {
        restTemplate = new RestTemplate();
        mapper = new ObjectMapper();
    }

    @RequestMapping("/horodateurs/findAll")
    public Iterable<Horodateur> findAll() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 100000)
    public void update() throws IOException {
        // GET the data from La Rochelle OpenData API
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

        // Parse the json
        String data = mapper.readTree(response.getBody()).at("/opendata/answer/data").toString();
        List<Horodateur> horodateurs = Arrays.asList(mapper.readValue(data, Horodateur[].class));

        // Save in database
        repository.saveAll(horodateurs);
    }
}
