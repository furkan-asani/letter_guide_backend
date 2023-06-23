package com.letter_guide.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.letter_guide.models.SummarizedDocument;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class OpenAIResponseParserService {
   public SummarizedDocument parseResponse(String openAIResponse) {
      ObjectMapper mapper = new ObjectMapper();
      try {
         SummarizedDocument model = mapper.readValue(openAIResponse, SummarizedDocument.class);

         return model;
      }
      catch ( JsonProcessingException e ) {
         throw new RuntimeException("The response could not be parsed correctly. OpenAI responded with the follwing: openAIResponse", e);
      }
   }
}
