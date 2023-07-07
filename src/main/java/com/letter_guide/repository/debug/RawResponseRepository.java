package com.letter_guide.repository.debug;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.letter_guide.models.debug.RawResponse;


public interface RawResponseRepository extends MongoRepository<RawResponse, String> {}
