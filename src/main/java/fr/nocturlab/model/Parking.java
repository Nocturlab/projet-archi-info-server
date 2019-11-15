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
/**
 * Parking en temps réel
 */
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
    private Double x;

    @JsonProperty("dp_y")
    private Double y;

    @JsonProperty("dp_place_disponible")
    private Integer placesDisponibles;

    private String type;

    private Integer tarification;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDate() {
        return date;
    }

    public Integer getNombresPlaces() {
        return nombresPlaces;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Integer getPlacesDisponibles() {
        return placesDisponibles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTarification() {
        return tarification;
    }

    public void setTarification(Integer tarification) {
        this.tarification = tarification;
    }
}