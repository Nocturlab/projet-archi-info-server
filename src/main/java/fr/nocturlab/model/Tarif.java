package fr.nocturlab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.IOException;
import java.util.*;

@Entity
@Getter
@Setter
@JsonDeserialize(using = TarifDeserializer.class)
public class Tarif {

    public Tarif() {}

    public Tarif(int id, String libelle, Collection<String> mentions, Collection<Tarification> tarifications) {
        this.id = id;
        this.libelle = libelle;
        this.mentions = mentions;
        this.tarifications = tarifications;
    }

    @Id
    @JsonProperty("pmt_id")
    private int id;

    @JsonProperty("mtp_libelle")
    private String libelle;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "mentions", nullable = false)
    @CollectionTable(name = "tarif_mentions", joinColumns = @JoinColumn(name = "tarif_id"))
    private Collection<String> mentions;

    @JsonProperty("horaires_tarification")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="Address",
            joinColumns = @JoinColumn( name = "TARIF_ID"),
            inverseJoinColumns = @JoinColumn( name = "TARIFICATION_ID"))
    private Collection<Tarification> tarifications;
}

class TarifDeserializer extends StdDeserializer<Tarif> {

    public TarifDeserializer() { this(null); }
    public TarifDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Tarif deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode root = parser.getCodec().readTree(parser);

        int pmt_id = root.get("pmt_id").asInt();
        String mtp_libelle = root.get("mtp_libelle").asText();

        Set<String> mentions = new HashSet<>();
        ArrayNode mentions_nodes = (ArrayNode) root.get("mentions").get("row");
        mentions_nodes.forEach(
            (node) -> mentions.add(node.get("mention_texte").asText())
        );

        Set<Tarification> tarifications = null;
        if (root.get("horaires_tarification") != null) {
            tarifications = new HashSet<>();

            ArrayNode tarifications_nodes = (ArrayNode) root.get("horaires_tarification").get("row");
            for (JsonNode tarif_node : tarifications_nodes) {
                int pht_id = tarif_node.get("pht_id").asInt();
                String pht_nom = tarif_node.get("pht_nom").asText();
                String pht_libelle = tarif_node.get("pht_libelle").asText();

                Map<String, Double> volume_horaire = new HashMap<>();

                JsonNode horaire_nodes = tarif_node.get("volume_horaire").get("row");

                if (horaire_nodes.getNodeType() == JsonNodeType.ARRAY) {
                    for (JsonNode node : horaire_nodes) {
                        String pvh_libelle = node.get("pvh_libelle").asText();
                        Double pt_tarif = node.get("pt_tarif").asDouble();
                        volume_horaire.put(pvh_libelle, pt_tarif);
                    }

                } else {
                    String pvh_libelle = horaire_nodes.get("pvh_libelle").asText();
                    Double pt_tarif = horaire_nodes.get("pt_tarif").asDouble();
                    volume_horaire.put(pvh_libelle, pt_tarif);
                }

                tarifications.add(new Tarification(pht_id, pht_nom, pht_libelle, volume_horaire));
            }
        }

        return new Tarif(pmt_id, mtp_libelle, mentions, tarifications);
    }
}

@Entity
@Getter
@Setter
@Table(name = "TARIFICATION")
class Tarification {

    public Tarification() {}

    public Tarification(int id, String nom, String libelle, Map<String, Double> volume) {
        this.id = id;
        this.nom = nom;
        this.libelle = libelle;
        this.volume = volume;
    }

    @Id
    @JsonProperty("pht_id")
    private int id;

    @JsonProperty("pht_nom")
    private String nom;

    @JsonProperty("pht_libelle")
    private String libelle;

    @JsonProperty("volume_horaire")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "volume")
    @CollectionTable(name = "tarification_volume", joinColumns = @JoinColumn(name = "tarification_id"))
    private Map<String, Double> volume;
}
