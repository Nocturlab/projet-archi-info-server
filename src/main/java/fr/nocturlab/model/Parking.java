package fr.nocturlab.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "parking")
@Getter
@Setter
@JsonIgnoreProperties("dp_id")
public class Parking {

    @Id
    @JsonProperty("dp_parc_id")
    private Integer id;

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

}