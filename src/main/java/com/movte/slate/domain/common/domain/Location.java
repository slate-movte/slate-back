package com.movte.slate.domain.common.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
public class Location {
	@Column(nullable = false)
	private double longitude;
	@Column(nullable = false)
	private double latitude;

	private String province; // 도
	private String city; // 시
	private String county; // 군
	private String district; // 구
}
