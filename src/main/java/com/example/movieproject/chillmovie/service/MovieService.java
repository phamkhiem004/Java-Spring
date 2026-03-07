package com.example.movieproject.chillmovie.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.movieproject.chillmovie.entity.Movie;
import com.example.movieproject.chillmovie.respository.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieByID(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);// do findById trả về Optional để tránh lỗi
                                                                     // NullPointerException khi không tìm thấy movie
                                                                     // với id đó
        if (movieOptional.isPresent()) {
            return movieOptional.get();// nếu movie tồn tại, trả về movie đó
        }
        return null;
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(Long id, Movie movie) {
        Movie existingMovie = getMovieByID(id);
        if (existingMovie != null) {
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setDescription(movie.getDescription());
            existingMovie.setTrailerUrl(movie.getTrailerUrl());
            existingMovie.setPosterUrl(movie.getPosterUrl());
            existingMovie.setDuration(movie.getDuration());
            existingMovie.setReleaseDate(movie.getReleaseDate());
            existingMovie.setCountry(movie.getCountry());
            existingMovie.setLanguage(movie.getLanguage());
            existingMovie.setAgeLimit(movie.getAgeLimit());
            return movieRepository.save(existingMovie);
        }
        return null;
    }

}
