package fr.nocturlab.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_")
@Getter @Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
	private String pseudo;
	private String email;
	@Column(insertable = false, updatable = false)
	private UUID token;
	@OneToMany
	@JoinTable(name = "user_password")
	@JsonIgnore
	private List<Password> pass;
	@Column(name = "creation_date", insertable = false, updatable = false)
	private LocalDateTime creationDate;
	@ManyToOne
	private Language language;
	
	@OneToMany
	@JoinTable(name = "user_access")
	private List<Access> accessList;
	@OneToMany
	@JoinTable(name = "user_group")
	private List<Group> groupList;
	
	public User(){}

	public User(String pseudo, String email, Password pass, Language lang){
		this.pseudo = pseudo;
		this.email = email;
		this.pass = new ArrayList<Password>();
		this.pass.add(pass);
		this.token = UUID.randomUUID();
		this.creationDate = LocalDateTime.now();
		this.language = lang;
		this.accessList = new ArrayList<Access>();
		this.groupList = new ArrayList<Group>();
	}
}