package com.letter_guide.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letter_guide.models.SummarizedDocument;
import com.letter_guide.service.SummariesService;


@RestController
@RequestMapping("/api")
public class SummariesController {

   private final SummariesService _summariesService;

   public SummariesController( SummariesService _summariesService ) {this._summariesService = _summariesService;}
   @GetMapping("/{userId}")
   public ResponseEntity<List<SummarizedDocument>> getAllSummariesByUserId(@PathVariable String userId) {
      List<SummarizedDocument> allDocumentsByUserId = _summariesService.getAllDocumentsByUserId(userId);

      if ( allDocumentsByUserId == null || allDocumentsByUserId.isEmpty() ) {
         return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(allDocumentsByUserId);
   }

}
