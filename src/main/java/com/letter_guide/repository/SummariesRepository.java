package com.letter_guide.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.letter_guide.models.SummarizedDocument;


public interface SummariesRepository extends MongoRepository<SummarizedDocument, String> {
   List<SummarizedDocument> findByUserId(String userId);
}
