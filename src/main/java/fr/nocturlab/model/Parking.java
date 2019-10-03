package fr.nocturlab.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Entity(name = "parking_")
@Getter
@Setter
public class Parking {

    private static final String url = "https://opendata.larochelle.fr/webservice/?service=getData&key=WrY71ysb6kBrpTv7&db=stationnement&table=disponibilite_parking";

    @Id
    @JsonProperty("dp_id")
    private Integer id;

    @JsonProperty("dp_parc_id")
    private Integer parcId;

    @JsonProperty("dp_libelle")
    private String libelle;

    @JsonProperty("dp_date")
    private String date;

    @JsonProperty("dp_nb_places")
    private Integer nombresPlaces;

    @JsonProperty("dp_x")
    private Double y;

    @JsonProperty("dp_y")
    private Double x;

    @JsonProperty("dp_place_disponible")
    private Integer placesDisponibles;


    /**
     * Mettre à jour les données des parkings
     */
    public void update()
    {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<MultiValueMap<String, String>> httpEntity =
                new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);


        ResponseEntity<Parking> parkings = null;

        try {
            parkings = restTemplate.exchange(url, HttpMethod.GET ,httpEntity, Parking.class);
        } catch (Exception e) {
            //TODO: handle exception
        }

    }
}