package fr.nocturlab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Tarif {

    @Id
    @JsonProperty("pmt_id")
    private int id;

    @JsonProperty("mtp_libelle")
    private String libelle;

    @JsonProperty("mentions")
    private List<String> mentions;

    @JsonProperty("horaires_tarification")
    private List<Tarification> tarifications;

    @Entity
    @Getter
    @Setter
    private class Tarification {
        @Id
        @JsonProperty("pht_id")
        private int id;

        @JsonProperty("pht_nom")
        private String nom;

        @JsonProperty("pht_libelle")
        private String libelle;

        @JsonProperty("volume_horaire")
        private Map<String, Double> volume;
    }
}
