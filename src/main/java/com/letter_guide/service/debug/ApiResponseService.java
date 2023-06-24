package com.letter_guide.service.debug;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letter_guide.models.api.openAI.response.OpenAIChatCompletionResponse;
import com.letter_guide.models.debug.RawResponse;


@Service
public class ApiResponseService {

   private final MongoRepository<RawResponse, String> repository;
   private final MongoRepository<OpenAIChatCompletionResponse, String> parsedResponseRepository;

   @Autowired
   public ApiResponseService(MongoRepository<RawResponse, String> repository, MongoRepository<OpenAIChatCompletionResponse, String> parsedResponseRepository ) {
      this.repository = repository;
      this.parsedResponseRepository = parsedResponseRepository;
   }

   public void saveRawResponse(String rawJson) {
      RawResponse apiResponse = new RawResponse();
      apiResponse.setRawJson(rawJson);

      repository.save(apiResponse);
   }

   public void saveParsedResponse( String rawJson ) throws JsonProcessingException {
      ObjectMapper mapper = new ObjectMapper();
      OpenAIChatCompletionResponse openAIChatCompletionResponse = mapper.readValue(rawJson, OpenAIChatCompletionResponse.class);

      parsedResponseRepository.save(openAIChatCompletionResponse);
   }
}
