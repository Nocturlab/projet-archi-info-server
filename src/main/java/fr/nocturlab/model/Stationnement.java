package fr.nocturlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Parking de stationnement
 */
@Entity(name = "stationnement")
@Getter
@Setter
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getNb_places() {
        return nb_places;
    }

    public void setNb_places(Integer nb_places) {
        this.nb_places = nb_places;
    }

    public Double getParking_x() {
        return parking_x;
    }

    public void setParking_x(Double parking_x) {
        this.parking_x = parking_x;
    }

    public Double getParking_y() {
        return parking_y;
    }

    public void setParking_y(Double parking_y) {
        this.parking_y = parking_y;
    }

    public Integer getTarification() {
        return tarification;
    }

    public void setTarification(Integer tarification) {
        this.tarification = tarification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
