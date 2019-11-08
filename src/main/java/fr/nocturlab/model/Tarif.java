package fr.nocturlab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Tarif {

    @Id
    @JsonProperty("tar_id")
    private int id;

    @JsonProperty("tar_periode_libelle")
    private String periodeLibelle;

    @JsonProperty("tar_duree_min")
    private String dureeMin;

    @JsonProperty("tar_duree_max")
    private String dureeMax;

    @JsonProperty("tar_parking_nom")
    private String nomParking;

    @JsonProperty("tar_parking_id")
    private String idParking;

    @JsonProperty("tar_duree")
    private String duree;

    @JsonProperty("tar_periode_date_fin")
    private String periodeDateFin;

    @JsonProperty("tar_periode_date_debut")
    private String periodeDateDebut;
}
