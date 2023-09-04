package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.DirectorBefore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorBeforeRepository extends JpaRepository<DirectorBefore, String> {

}
