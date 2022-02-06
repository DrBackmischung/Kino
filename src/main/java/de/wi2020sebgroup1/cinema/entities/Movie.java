package de.wi2020sebgroup1.cinema.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	@NotNull
	private String title;
	
	@Column
	@NotNull
	private String originalTitle;
	
	@Column
	@NotNull
	private String language;
	
	@Column
	@NotNull
	private double duration;
	
	@Column
	@NotNull
	private String genre;
	
	@Column
	@NotNull
	private String director;
	
	@Column
	@NotNull
	private String actors;
	
	@Column(columnDefinition = "TEXT")
	@NotNull
	private String description;
	
	@Column(columnDefinition = "TEXT")
	@NotNull
	private String originalDescription;
	
	@Column
	@NotNull
	private String pictureLink;
	
	@Column
	@NotNull
	private String trailerLink;
	
	@Column
	@NotNull
	private int FSK;
	
	
	public Movie() {
		
	}
	
	public Movie(@NotNull String titel, @NotNull String originalTitle , @NotNull String language, @NotNull double duration, @NotNull String director,
			@NotNull String actors,@NotNull String description, @NotNull String originalDescription, @NotNull String pictureLink, 
			@NotNull String trailerLink, @NotNull String genre, @NotNull int FSK) {
		super();
		this.title = titel;
		this.originalTitle = originalTitle;
		this.genre = genre;
		this.language = language;
		this.duration = duration;
		this.director = director;
		this.actors = actors;
		this.description = description;
		this.originalDescription = originalDescription;
		this.pictureLink = pictureLink;
		this.trailerLink = trailerLink;
		this.FSK = FSK;
	}

	public String getDirector() {
		return director;
	}
	
	public String getActors() {
		return actors;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getOriginalDescription() {
		return originalDescription;
	}
	
	public String getOriginalTitle() {
		return originalTitle;
	}
	
	public String getTrailerLink() {
		return trailerLink;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPictureLink() {
		return pictureLink;
	}
	
	public int getFSK() {
		return FSK;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setActors(String actors) {
		this.actors = actors;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setOriginalDescription(String originalDescription) {
		this.originalDescription = originalDescription;
	}
	
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}
	
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTitle(String titel) {
		this.title = titel;
	}
	
	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}
	
	public void setFSK(int fSK) {
		FSK = fSK;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		long temp;
		temp = Double.doubleToLongBits(duration);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((pictureLink == null) ? 0 : pictureLink.hashCode());
		result = prime * result + ((trailerLink == null) ? 0 : trailerLink.hashCode());
		result = prime * result + ((originalTitle == null) ? 0 : originalTitle.hashCode());
		result = prime * result + ((originalDescription == null) ? 0 : originalDescription.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + FSK;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (description != other.description)
			return false;
		if (director != other.director)
			return false;
		if (duration != other.duration)
			return false;
		if (id != other.id)
			return false;
		if (language != other.language)
			return false;
		if (pictureLink != other.pictureLink)
			return false;
		if (title != other.title)
			return false;
		if (trailerLink != other.trailerLink)
			return false;
		if (originalTitle != other.originalTitle)
			return false;
		if (originalDescription != other.originalDescription)
			return false;
		if (actors != other.actors)
			return false;
		if (genre != other.genre)
			return false;
		if (FSK != other.FSK)
			return false;
		return true;
	}

}
