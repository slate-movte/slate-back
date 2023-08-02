package com.movte.slate.domain.movie.domain;

import com.movte.slate.domain.user.domain.User;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cast {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	@JoinColumn(name = "cast_id")
	private List<Actor> actorList;

	@OneToMany
	@JoinColumn(name = "user_id")
	private List<User> userList;
}
