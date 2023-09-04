package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, String> {
    Actor findByActorIdBef(String actorId);
}
