package com.letter_guide.clients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource("classpath:secrets.properties")
@Component
public class OpenAIApiClient {
   @Autowired
   private final HttpClient httpClient;
   private static final String OPEN_AI_API = "https://api.openai.com/v1/chat/completions";
   @Value("${openai.api.key}")
   private  String API_KEY;

   @Autowired
   public OpenAIApiClient( HttpClient httpClient ) {
      this.httpClient = httpClient;

   }

   public String sendPrompt( String body ) {
      HttpRequest request = createRequest(body);

      HttpResponse<String> response;
      try {
         response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
         if ( response.statusCode() >= 400 ) {
            throw new RuntimeException("The OpenAI api must be unavailable due to a reason. Response data: "+ response.body());
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

}