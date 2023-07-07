package com.letter_guide.clients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.letter_guide.service.debug.ApiResponseService;


@PropertySource("classpath:secrets.properties")
@Component
public class OpenAIApiClient {

   private static final String             OPEN_AI_API = "https://api.openai.com/v1/chat/completions";
   private final        HttpClient         httpClient;
   private final        ApiResponseService _apiResponseService;
   @Value("${openai.api.key}")
   private              String             API_KEY;

   @Autowired
   public OpenAIApiClient( HttpClient httpClient, ApiResponseService apiResponseService ) {
      this.httpClient = httpClient;
      _apiResponseService = apiResponseService;
   }

   public String sendPrompt( String body ) {
      HttpRequest request = createRequest(body);

      HttpResponse<String> response;
      try {
         response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
         saveResponseToDb(response);

         if ( response.statusCode() >= 400 ) {
            throw new RuntimeException("The OpenAI api must be unavailable due to a reason. Response data: " + response.body());
         }

         return response.body();
      }
      catch ( IOException | InterruptedException e ) {
         throw new RuntimeException(e);
      }
   }

   protected HttpRequest createRequest( String body ) {
      HttpRequest request;
      try {
         request = HttpRequest.newBuilder()
               .uri(new URI(OPEN_AI_API))
               .header("Content-Type", "application/json")
               .header("Authorization", "Bearer " + API_KEY)
               .POST(HttpRequest.BodyPublishers.ofString(body))
               .build();
      }
      catch ( URISyntaxException e ) {
         throw new RuntimeException(e);
      }
      return request;
   }

   /**
    * ONLY for non prod environments. Used for debugging purposes
    * Saves the HTTP response to the database.
    * @param response the HTTP response to save
    */
   @Profile("!prod")
   private void saveResponseToDb( HttpResponse<String> response ) {
      _apiResponseService.saveRawResponse("Status code: " + response.statusCode() + "\n body: " + response.body());

      try {
         _apiResponseService.saveParsedResponse(response.body());
      }
      catch ( JsonProcessingException ignored ) {
      }
   }

}
