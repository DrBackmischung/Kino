package de.wi2020sebgroup1.cinema.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="vorstellung")
public class Show {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	@NotNull
	private java.sql.Date showDate;
	
	@Column
	@NotNull
	private java.sql.Time startTime;
	
	@Column
	@NotNull
	private java.sql.Time endTime;
	
	@ManyToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "movie_id", referencedColumnName = "id")
	private Movie movie;
	
	@ManyToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "cinema_id", referencedColumnName = "id")
	private Cinema cinema;
	
	@ManyToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "cinemaRoom_id", referencedColumnName = "id")
	private CinemaRoom cinemaRoom;
	
	@OneToMany(mappedBy="show")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Seat> seats = new ArrayList<>();
	
	public Show() {
		
	}
	
	public Show(Date showDate, Time startTime, Time endTime, Movie movie, Cinema cinema, CinemaRoom cinemaRoom) {
		super();
		this.showDate = showDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.movie = movie;
		this.cinema = cinema;
		this.cinemaRoom = cinemaRoom;
	}

	public Cinema getCinema() {
		return cinema;
	}
	
	public CinemaRoom getCinemaRoom() {
		return cinemaRoom;
	}
	
	public Date getShowDate() {
		return showDate;
	}
	
	public Time getEndTime() {
		return endTime;
	}
	
	public UUID getId() {
		return id;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public void setCinemaRoom(CinemaRoom cinemaRoom) {
		this.cinemaRoom = cinemaRoom;
	}
	
	public void setShowDate(Date date) {
		this.showDate = date;
	}
	
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cinema == null) ? 0 : cinema.hashCode());
		result = prime * result + ((cinemaRoom == null) ? 0 : cinemaRoom.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((showDate == null) ? 0 : showDate.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		Show other = (Show) obj;
		if (cinema != other.cinema)
			return false;
		if (cinemaRoom != other.cinemaRoom)
			return false;
		if (endTime != other.endTime)
			return false;
		if (id != other.id)
			return false;
		if (movie != other.movie)
			return false;
		if (showDate != other.showDate)
			return false;
		if (startTime != other.startTime)
			return false;
		return true;
	}
}
