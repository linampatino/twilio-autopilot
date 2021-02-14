package com.twilio.moviesbot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twilio.moviesbot.models.QueriedMovie;

@Repository
public interface QueriedMovieRepository extends CrudRepository<QueriedMovie, Long>{

	public QueriedMovie findByDialogueSid(String dialogueSid);
}
