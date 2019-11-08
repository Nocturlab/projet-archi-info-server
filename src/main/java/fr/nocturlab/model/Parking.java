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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNombresPlaces() {
        return nombresPlaces;
    }

    public void setNombresPlaces(Integer nombresPlaces) {
        this.nombresPlaces = nombresPlaces;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(Integer placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }
}