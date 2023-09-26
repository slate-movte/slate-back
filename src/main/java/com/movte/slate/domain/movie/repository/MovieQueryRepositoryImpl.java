package com.movte.slate.domain.movie.repository;


import static com.movte.slate.domain.movie.domain.QActor.actor;
import static com.movte.slate.domain.movie.domain.QMovie.movie;
import static com.movte.slate.domain.movie.domain.QMovieActor.movieActor;
import static com.movte.slate.domain.snapshot.domain.QScene.scene;

import com.movte.slate.domain.movie.domain.Movie;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class MovieQueryRepositoryImpl implements MovieQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Movie> selectListMovieWithActorByKeywordAndLastId(String keyword, Long lastId) {
        return queryFactory.select(movie)
            .from(movie)
            .leftJoin(movie.movieActors, movieActor)
            .leftJoin(movieActor.actor, actor)
            .where(lastIdAfter(lastId), isSearchKeyword(keyword))
            .groupBy(movie.movieId)
            .orderBy(movie.movieId.asc())
            .limit(5)
            .fetch();
    }

    @Override
    public Movie selectMovieWithActorAndScene(Long movieId) {
        return queryFactory.select(movie)
            .from(movie)
            .leftJoin(movie.movieActors, movieActor)
            .leftJoin(movieActor.actor, actor)
            .leftJoin(movie.scenes, scene)
            .where(movie.movieId.eq(movieId))
            .fetchOne();
    }

    private BooleanExpression isSearchKeyword(String keyword) {
        if (!StringUtils.hasText(keyword)) { // null 이거나 공백이면
            return null;
        }
        return isContainsTitleKeywords(keyword).or(isContainActorNames(keyword));
    }

    private BooleanExpression isContainsTitleKeywords(String keyword) {
        return movie.title.contains(keyword);
    }

    private BooleanExpression isContainActorNames(String keyword) {
        return actor.name.contains(keyword);
    }

    private BooleanExpression lastIdAfter(Long lastId) {
        return movie.movieId.gt(lastId);
    }
}
