package com.letter_guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letter_guide.models.SummarizedDocument;
import com.letter_guide.repository.SummariesRepository;


@Service
public class SummaryFetchService {

   private final SummariesRepository _summariesRepository;

   @Autowired
   public SummaryFetchService(SummariesRepository summariesRepository) {
      this._summariesRepository= summariesRepository;
   }

   public List<SummarizedDocument> getAllDocumentsByUserId(String userId) {
      return _summariesRepository.findByUserId(userId);

   }
}