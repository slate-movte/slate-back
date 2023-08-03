package com.movte.slate.domain.place.domain;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "cafe")
@DiscriminatorValue("cafe")
public class Cafe extends Place {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private OfficeHours officeHours;

	@OneToMany(mappedBy = "cafe")
	private List<Menu> menuList;
}
