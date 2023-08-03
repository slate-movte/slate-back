package com.movte.slate.domain.place.domain;

import com.movte.slate.domain.common.domain.Location;
import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.movie.domain.Scene;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

@Entity
@Table(name = "sceneplace")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class ScenePlace extends Place {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String sceneDescription;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@OneToMany(mappedBy = "scenePlace")
	private List<Scene> sceneList;
}
