package com.movte.slate.domain.movie.service;

import com.movte.slate.domain.movie.domain.Director;
import com.movte.slate.domain.movie.domain.DirectorBefore;
import com.movte.slate.domain.movie.repository.DirectorBeforeRepository;
import com.movte.slate.domain.movie.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorBeforeRepository directorBeforeRepository;
    private final DirectorRepository directorRepository;

    public void run() throws Exception {
        try {
            List<DirectorBefore> lists = directorBeforeRepository.findAll();

            for (DirectorBefore directorBefore : lists) {
                Director director = directorRepository.findByDirectorIdBef(directorBefore.getDirectorId());
                if (director == null) {
                    director = Director.builder()
                            .directorIdBef(directorBefore.getDirectorId())
                            .name(directorBefore.getName())
                            .build();

                    directorRepository.save(director);
                }
            }
        } catch (Exception e) {
            log.info(e);
        }
    }
}
