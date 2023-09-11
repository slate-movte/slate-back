package com.movte.slate.domain.movie.dto;


import com.movte.slate.domain.movie.domain.Movie;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MovieDetailResponseDto {

    private Long id;
    private String title;
    private String director;
    private String company;
    private Integer openYear;
    private String openDate;
    private String rating; // 관람 등급 ex 15세..
    private String posterUrl;
    private String audienceCount;// 관람인원
    private String plot; //줄거리

    // 출연진
    private List<String> movieCastList;

    // 씬 이미지들
    private List<SceneImageResponseDto> sceneImages;

    @Builder
    public MovieDetailResponseDto(Long id, String title, String director, String company,
        Integer openYear, String openDate, String rating, String posterUrl, String audienceCount,
        String plot, List<String> movieCastList, List<SceneImageResponseDto> sceneImages) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.company = company;
        this.openYear = openYear;
        this.openDate = openDate;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.audienceCount = audienceCount;
        this.plot = plot;
        this.movieCastList = movieCastList;
        this.sceneImages = sceneImages;
    }

    public static MovieDetailResponseDto from(Movie movie) {
        List<String> movieCast = movie.getMovieActors().stream()
            .map((movieActor -> movieActor.getActor().getName()))
            .toList();

        return MovieDetailResponseDto.builder()
            .id(movie.getMovieId())
            .title(movie.getTitle())
            .director(movie.getDirector().getName())
            .company(movie.getCompany())
            .openYear(movie.getOpenYear())
            .openDate(movie.getOpenDate())
            .rating(movie.getRating())
            .posterUrl(movie.getPosterUrl())
            .audienceCount(movie.getAudienceCount())
            .plot(movie.getPlot())
            .movieCastList(movieCast)
            .sceneImages(movie.getScenes().stream().map(SceneImageResponseDto::from).toList())
            .build();
    }
}
