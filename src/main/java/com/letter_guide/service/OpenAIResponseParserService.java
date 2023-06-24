package com.letter_guide.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.letter_guide.models.SummarizedDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letter_guide.models.api.openAI.response.Choice;
import com.letter_guide.models.api.openAI.response.OpenAIChatCompletionResponse;


@Service
public class OpenAIResponseParserService {
   public SummarizedDocument parseResponse(String openAIResponse) {
      ObjectMapper mapper = new ObjectMapper();
      try {
         OpenAIChatCompletionResponse openAIChatCompletionResponse = parseOpenAIResponseIntoResponseModel(openAIResponse, mapper);

         return parseResponseModelIntoSummarizedDocument(mapper, openAIChatCompletionResponse);
      }
      catch ( JsonProcessingException e ) {
         throw new RuntimeException("The response could not be parsed correctly. OpenAI responded with the following: openAIResponse", e);
      }
   }

   private SummarizedDocument parseResponseModelIntoSummarizedDocument( ObjectMapper mapper, OpenAIChatCompletionResponse openAIChatCompletionResponse )
         throws JsonProcessingException {
      Choice choice = openAIChatCompletionResponse.getChoices().get(0);
      if ( choice == null ) {
         throw new RuntimeException("The response is invalid since it does not contain the necessary data. Response: " + openAIChatCompletionResponse);
      }

      return mapper.readValue(choice.getMessage().getContent(), SummarizedDocument.class);
   }

   private OpenAIChatCompletionResponse parseOpenAIResponseIntoResponseModel( String openAIResponse, ObjectMapper mapper ) throws JsonProcessingException {
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      return mapper.readValue(openAIResponse, OpenAIChatCompletionResponse.class);
   }
}
