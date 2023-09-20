package com.movte.slate.domain.attraction.dto.response;

import com.movte.slate.domain.movie.domain.Movie;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieListResponseDto {

    private Long movieId;
    private String title;
    private String posterUrl;
    private String openDate;
    private Integer openYear;
    private List<String> movieCastList;

    @Builder
    public MovieListResponseDto(Long movieId, String title, String posterUrl, String openDate,
        Integer openYear, List<String> movieCastList) {
        this.movieId = movieId;
        this.title = title;
        this.posterUrl = posterUrl;
        this.openDate = openDate;
        this.openYear = openYear;
        this.movieCastList = movieCastList;
    }

    public static MovieListResponseDto from(Movie movie) {
        List<String> movieCast = movie.getMovieActors().stream()
            .map((movieActor -> movieActor.getActor().getName()))
            .toList();
        String posterUrl = null;
        String posterOriginUrl = movie.getPosterUrl();
        if (posterOriginUrl != null) {
            if (posterOriginUrl.split("\\|").length > 0) {
                posterUrl = posterOriginUrl.split("\\|")[0];
            }
        }

        return MovieListResponseDto.builder()
            .movieId(movie.getMovieId())
            .title(movie.getTitle())
            .posterUrl(posterUrl)
            .openDate(movie.getOpenDate())
            .openYear(movie.getOpenYear())
            .movieCastList(movieCast)
            .build();
    }
}
