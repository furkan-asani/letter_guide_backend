package com.letter_guide.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letter_guide.models.SummarizedDocument;


class OpenAIResponseParserServiceTest {

   private final OpenAIResponseParserService _openAIResponseParserService = new OpenAIResponseParserService();

   @Test
   void testParseResponseIntoSummarizedDocument() {
      //given
      ObjectMapper objectMapper = new ObjectMapper();
      SummarizedDocument summarizedDocument = new SummarizedDocument();
      summarizedDocument.setSummary("This is a summary");
      summarizedDocument.setCategory("IRR");
      summarizedDocument.setTasks(new ArrayList<>(List.of("Hello", "Hello")));
      summarizedDocument.setNextSteps(new ArrayList<>(List.of("Hullo", "Hullo")));
      summarizedDocument.setDeadlines(new ArrayList<>(List.of("Hzllo", "Hzllo")));
      summarizedDocument.setPriority("High");
      summarizedDocument.setUserId("1");
      String openAIResponse;
      try {
         openAIResponse = objectMapper.writeValueAsString(summarizedDocument);
      }
      catch ( JsonProcessingException e ) {
         throw new RuntimeException(e);
      }

      //when
      SummarizedDocument parsedSummarizedDocument = null;
      if ( openAIResponse != null ) {

         parsedSummarizedDocument = _openAIResponseParserService.parseResponse(openAIResponse);
      }

      //then
      assertThat(parsedSummarizedDocument).usingRecursiveComparison().isEqualTo(summarizedDocument);
   }
}