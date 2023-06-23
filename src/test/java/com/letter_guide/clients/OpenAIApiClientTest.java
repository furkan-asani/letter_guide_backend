package com.letter_guide.clients;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import lombok.SneakyThrows;


class OpenAIApiClientTest {
   @Mock
   private HttpClient httpClient;

   @InjectMocks
   private OpenAIApiClient client;

   @SneakyThrows
   @Test
   void testSendPrompt() {
      MockitoAnnotations.openMocks(this);

      HttpResponse<String> httpResponse = Mockito.mock(HttpResponse.class);
      String responseBody = "{ \"id\": \"chatcmpl-6p9XYPYSTTRi0xEviKjjilqrWU2Ve\", [...] }";
      Mockito.when(httpResponse.body()).thenReturn(responseBody);
      Mockito.when(httpResponse.statusCode()).thenReturn(200);

      Mockito.when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

      String response = client.sendPrompt("aaa");
      assertThat(response.equals(responseBody));
   }
}