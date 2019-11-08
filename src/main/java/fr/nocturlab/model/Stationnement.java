package fr.nocturlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "stationnement")
@Getter
@Setter
/**
 * Parking de stationnement
 */
public class Stationnement {

    @Id
    @JsonProperty("parking_id")
    private Integer id;

    @JsonProperty("parking_nom")
    private String nom;

    @JsonProperty("parking_libelle")
    private String libelle;

    @JsonProperty("parking_nb_places")
    private Integer nb_places;

    @JsonProperty("parking_x")
    private Double parking_x;

    @JsonProperty("parking_y")
    private Double parking_y;

    @JsonProperty("parking_mode_tarification")
    private Integer tarification;

    @JsonProperty("parking_type")
    private String type;
}
