package com.letter_guide.clients;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
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

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @SneakyThrows
   @Test
   void testSendPrompt() {
      //given
      HttpResponse<String> httpResponse = Mockito.mock(HttpResponse.class);
      String responseBody = "{ \"id\": \"chatcmpl-6p9XYPYSTTRi0xEviKjjilqrWU2Ve\", [...] }";
      Mockito.when(httpResponse.body()).thenReturn(responseBody);
      Mockito.when(httpResponse.statusCode()).thenReturn(200);
      Mockito.when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

      //when
      String response = client.sendPrompt("aaa");

      //then
      assertThat(response.equals(responseBody));
   }

   @Test
   void testCreateRequest() {
      //given
      String promptRequestBody = "aaa";

      //when
      HttpRequest request = client.createRequest(promptRequestBody);

      //then
      assertThat(request).isNotNull();
      assertThat(request.uri()).hasHost("api.openai.com");
      assertThat(request.method()).isEqualTo("POST");
      assertThat(request.headers()).isNotNull();
      assertThat(request.headers().map()).containsKey("Authorization");
   }
}