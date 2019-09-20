package fr.nocturlab.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Password {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
    private byte[] value;
    private String encode; // Méthode de chiffrement du mot de passe.
    private boolean generated;
    @Column(name = "creation_date", insertable = false, updatable = false)
	private LocalDateTime creationDate;

	public Password() {
	}
	
	public Password(byte[] pass, String encode) {
		this.value = pass;
		this.encode = encode;
		this.creationDate = LocalDateTime.now();
	}
}