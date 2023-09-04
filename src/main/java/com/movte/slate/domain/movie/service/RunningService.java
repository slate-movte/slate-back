package com.movte.slate.domain.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class RunningService implements CommandLineRunner {

    private final ActorService actorService;
    private final MovieService movieService;
    private final DirectorService directorService;

    @Override
    public void run(String... args) throws Exception {
        directorService.run();
        movieService.run();
        actorService.run();
    }
}
