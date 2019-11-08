package fr.nocturlab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nocturlab.repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    }
}
