package com.letter_guide.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letter_guide.clients.OpenAIApiClient;
import com.letter_guide.models.SummarizedDocument;
import com.letter_guide.models.api.OpenAIApiRequest;


@Service
public class SummaryCreationService {

   public static final String SYSTEM = "system";
   public static final String GPT_3_5_TURBO = "gpt-3.5-turbo";

   private final OpenAIApiClient             _openAIApiClient;
   private final PromptCreationService       _promptCreationService;
   private final OpenAIResponseParserService _openAIResponseParserService;

   public SummaryCreationService( OpenAIApiClient _openAIApiClient, PromptCreationService _promptCreationService,
         OpenAIResponseParserService _openAIResponseParserService ) {
      this._openAIApiClient = _openAIApiClient;
      this._promptCreationService = _promptCreationService;
      this._openAIResponseParserService = _openAIResponseParserService;
   }

   public SummarizedDocument getSummarizedDocument(String extractedText) {

      String apiRequestBody = createOpenAIApiRequestBody(extractedText);
      String responseBody = _openAIApiClient.sendPrompt(apiRequestBody);

      return _openAIResponseParserService.parseResponse(responseBody);
   }

   protected String createOpenAIApiRequestBody( String extractedText ) {
      String prompt = _promptCreationService.createPrompt(extractedText);

      ArrayList<OpenAIApiRequest.Message> messages = new ArrayList<>();
      messages.add(new OpenAIApiRequest.Message(SYSTEM, prompt));


      OpenAIApiRequest openAIApiRequest = new OpenAIApiRequest(GPT_3_5_TURBO, messages, 0.2);
      ObjectMapper objectMapper = new ObjectMapper();
      try {
         return objectMapper.writeValueAsString(openAIApiRequest);
      }
      catch ( JsonProcessingException e ) {
         throw new RuntimeException("Error while writing the request object into a json body",e);
      }
   }
}
