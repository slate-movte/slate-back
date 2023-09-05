package com.movte.slate.domain.movie.domain;


import com.movte.slate.domain.attraction.domain.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class FilmLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmLocationId;
    @Embedded
    private Address address;

    @Builder
    public FilmLocation(Address address) {
        this.address = address;
    }
}
