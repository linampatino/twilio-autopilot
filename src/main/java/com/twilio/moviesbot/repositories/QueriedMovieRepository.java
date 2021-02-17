package com.twilio.moviesbot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twilio.moviesbot.models.QueriedMovie;

@Repository
public interface QueriedMovieRepository extends CrudRepository<QueriedMovie, Long> {

	public Optional<QueriedMovie> findByDialogueSid(String dialogueSid);

	public Optional<QueriedMovie> findByDialogueSidAndMovieId(String dialogueSid, String movieId);

	public List<QueriedMovie> findByMovieName(String movieName);
}
