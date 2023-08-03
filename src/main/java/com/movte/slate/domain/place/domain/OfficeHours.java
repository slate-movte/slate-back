package com.movte.slate.domain.place.domain;

import javax.persistence.Embeddable;

@Embeddable
public class OfficeHours {
	private long startHour;
	private long startMinute;

	private long endHour;
	private long endMinute;
}
