package com.movte.slate.domain.movie.domain;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String company;
	private String plot;

	private LocalDate openDate;
	private Genre genre;
	@Column(precision = 1, scale = 2)
	private BigInteger rating;
	private String posterUrl;
	private Long audienceCount;

	@ManyToOne
	@JoinColumn(name = "director_id")
	private Director director;
}
