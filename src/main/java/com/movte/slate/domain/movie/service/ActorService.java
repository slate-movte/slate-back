package com.movte.slate.domain.movie.service;

import com.movte.slate.domain.movie.domain.Actor;
import com.movte.slate.domain.movie.domain.ActorBefore;
import com.movte.slate.domain.movie.repository.ActorBeforeRepository;
import com.movte.slate.domain.movie.repository.ActorRepository;
import com.movte.slate.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;
    private final ActorBeforeRepository actorBeforeRepository;
    private final MovieRepository movieRepository;


    public void run() throws Exception {
        try {
            List<ActorBefore> lists = actorBeforeRepository.findAll();

            for (ActorBefore actorBefore : lists) {
                if(actorBefore.getActorId()==null) continue;
                Actor actor = actorRepository.findByActorIdBef(actorBefore.getActorId());
                if(actor!=null) continue;
                actor = Actor.builder()
                        .name(actorBefore.getName())
                        .actorIdBef(actorBefore.getActorId())
                        .build();
                actorRepository.save(actor);
            }

        } catch (Exception e) {
            log.info(e);
        }
    }
}
