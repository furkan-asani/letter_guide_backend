package com.letter_guide.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letter_guide.models.SummarizedDocument;
import com.letter_guide.service.SummariesFetchService;


@RestController
@RequestMapping("/api")
public class SummariesController {

   private final SummariesFetchService _summariesFetchService;

   public SummariesController( SummariesFetchService _summariesFetchService ) {this._summariesFetchService = _summariesFetchService;}
   @GetMapping("/{userId}")
   public ResponseEntity<List<SummarizedDocument>> getAllSummariesByUserId(@PathVariable String userId) {
      List<SummarizedDocument> allDocumentsByUserId = _summariesFetchService.getAllDocumentsByUserId(userId);

      if ( allDocumentsByUserId == null || allDocumentsByUserId.isEmpty() ) {
         return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(allDocumentsByUserId);
   }

}
