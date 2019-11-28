package fr.nocturlab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PlaceMR {

    @Id
    @JsonProperty("pr_id")
    private Integer id;

    @JsonProperty("pr_x")
    private Double x;

    @JsonProperty("pr_y")
    private Double y;

}
