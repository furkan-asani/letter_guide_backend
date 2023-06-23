package com.letter_guide.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letter_guide.models.SummarizedDocument;
import com.letter_guide.models.api.CreateSummarizedDocumentRequest;
import com.letter_guide.service.SummariesFetchService;
import com.letter_guide.service.SummaryCreationService;


@RestController
@RequestMapping("/api")
public class SummariesController {

   private final SummariesFetchService  _summariesFetchService;
   private final SummaryCreationService _summaryCreationService;

   public SummariesController( SummariesFetchService _summariesFetchService, SummaryCreationService _summaryCreationService ) {this._summariesFetchService = _summariesFetchService;
      this._summaryCreationService = _summaryCreationService;
   }
   @GetMapping("/{userId}")
   public ResponseEntity<List<SummarizedDocument>> getAllSummariesByUserId(@PathVariable String userId) {
      List<SummarizedDocument> allDocumentsByUserId = _summariesFetchService.getAllDocumentsByUserId(userId);

      if ( allDocumentsByUserId == null || allDocumentsByUserId.isEmpty() ) {
         return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(allDocumentsByUserId);
   }

   @PostMapping("/createSummary")
   public ResponseEntity<SummarizedDocument> createSummarizedDocument(@RequestBody CreateSummarizedDocumentRequest request) {
      SummarizedDocument summarizedDocument = _summaryCreationService.getSummarizedDocument(request.getExtractedText());

      return ResponseEntity.ok(summarizedDocument);
   }
}
