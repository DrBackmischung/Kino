package de.wi2020sebgroup1.cinema.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="news")
public class News {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	@NotNull
	private Date date;
	
	@Column
	@NotNull
	private Time time;
	
	@Column
	@NotNull
	private String header;
	
	@Column(columnDefinition = "TEXT")
	@NotNull
	private String content;
	
	@Column
	@NotNull
	private String pictureLink;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public News() {
		
	}

	public News(@NotNull Date date, @NotNull Time time, @NotNull String header, @NotNull String content,
			@NotNull String pictureLink, User user) {
		super();
		this.date = date;
		this.time = time;
		this.header = header;
		this.content = content;
		this.pictureLink = pictureLink;
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pictureLink == null) ? 0 : pictureLink.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		News other = (News) obj;
		if (content != other.content)
			return false;
		if (date != other.date)
			return false;
		if (header != other.header)
			return false;
		if (id != other.id)
			return false;
		if (pictureLink != other.pictureLink)
			return false;
		if (time != other.time)
			return false;
		if (user != other.user)
			return false;
		return true;
	}

}
