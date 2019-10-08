package fr.nocturlab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Horodateur {

    public Horodateur() {}
/*
    public Horodateur(int id, int numero, String voieLibelle, String type, String alimentation, String zone, double x, double y) {
        this.id = id;
        this.numero = numero;
        this.voieLibelle = voieLibelle;
        this.type = type;
        this.alimentation = alimentation;
        this.zone = zone;
        this.x = x;
        this.y = y;
    }*/

    @Id
    @JsonProperty("hor_id")
    private Integer id;

    @JsonProperty("hor_numero")
    private Integer numero;

    @JsonProperty("hor_voie_libelle")
    private String voieLibelle;

    @JsonProperty("hor_type")
    private String type;

    @JsonProperty("hor_alimentation")
    private String alimentation;

    @JsonProperty("hor_zone")
    private String zone;

    @JsonProperty("hor_x")
    private Double x;

    @JsonProperty("hor_y")
    private Double y;

}
