package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.ActorBefore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorBeforeRepository extends JpaRepository<ActorBefore, String> {

}
