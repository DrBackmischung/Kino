package de.wi2020sebgroup1.cinema.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	@NonNull
	private int plz;
	
	@Column
	@NotNull
	private String city;
	
	public City() {}	
	
	public City(int plz, @NotNull String city) {
		super();
		this.plz = plz;
		this.city = city;
	}

	public UUID getId() {
		return this.id;
	}
	
	public int getPlz() {
		return plz;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setPLZ(int PLZ) {
		this.plz = PLZ;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

}
