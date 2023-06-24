package com.letter_guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letter_guide.models.SummarizedDocument;
import com.letter_guide.repository.SummaryRepository;


@Service
public class SummaryFetchService {

   private final SummaryRepository _summaryRepository;

   @Autowired
   public SummaryFetchService( SummaryRepository summaryRepository ) {
      this._summaryRepository = summaryRepository;
   }

   public List<SummarizedDocument> getAllDocumentsByUserId(String userId) {
      return _summaryRepository.findByUserId(userId);

   }
}