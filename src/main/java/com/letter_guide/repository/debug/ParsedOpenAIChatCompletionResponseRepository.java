package com.letter_guide.repository.debug;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.letter_guide.models.api.openAI.response.OpenAIChatCompletionResponse;


public interface ParsedOpenAIChatCompletionResponseRepository extends MongoRepository<OpenAIChatCompletionResponse, String> {}
