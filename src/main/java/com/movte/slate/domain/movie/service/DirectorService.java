//package com.movte.slate.domain.movie.service;
//
//import com.movte.slate.domain.movie.domain.Director;
//import com.movte.slate.domain.movie.domain.DirectorBefore;
//import com.movte.slate.domain.movie.repository.DirectorBeforeRepository;
//import com.movte.slate.domain.movie.repository.DirectorRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Log4j2
//@Component
//@RequiredArgsConstructor
//public class DirectorService implements CommandLineRunner {
//
//    private final DirectorBeforeRepository directorBeforeRepository;
//    private final DirectorRepository directorRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            List<DirectorBefore> lists = directorBeforeRepository.findAll();
//
//            for (DirectorBefore directorBefore : lists) {
//                Director director = directorRepository.findByDirectorIdBef(directorBefore.getDirectorId());
//                if (director == null) {
//                    director = Director.builder()
//                            .directorIdBef(directorBefore.getDirectorId())
//                            .name(directorBefore.getName())
//                            .build();
//
//                    directorRepository.save(director);
//                }
//            }
//        } catch (Exception e) {
//            log.info(e);
//        }
//    }
//}
