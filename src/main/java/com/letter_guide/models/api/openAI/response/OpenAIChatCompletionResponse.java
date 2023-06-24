package com.letter_guide.models.api.openAI.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAIChatCompletionResponse {
   private String id;

   @Override
   public String toString() {
      return "OpenAIChatCompletionResponse{" + "id='" + id + '\'' + ", object='" + object + '\'' + ", created=" + created + ", choices=" + choices + ", usage="
            + usage + '}';
   }

   private String       object;
   private long         created;
   private List<Choice> choices;
   private Usage        usage;

   public String getId() {
      return id;
   }

   public void setId( String id ) {
      this.id = id;
   }

   public String getObject() {
      return object;
   }

   public void setObject( String object ) {
      this.object = object;
   }

   public long getCreated() {
      return created;
   }

   public void setCreated( long created ) {
      this.created = created;
   }

   public List<Choice> getChoices() {
      return choices;
   }

   public void setChoices( List<Choice> choices ) {
      this.choices = choices;
   }

   public Usage getUsage() {
      return usage;
   }

   public void setUsage( Usage usage ) {
      this.usage = usage;
   }

   // Getters and setters
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Usage {
   @JsonProperty("prompt_tokens")
   private int promptTokens;

   public int getPromptTokens() {
      return promptTokens;
   }

   public void setPromptTokens( int promptTokens ) {
      this.promptTokens = promptTokens;
   }

   public int getCompletionTokens() {
      return completionTokens;
   }

   public void setCompletionTokens( int completionTokens ) {
      this.completionTokens = completionTokens;
   }

   public int getTotalTokens() {
      return totalTokens;
   }

   public void setTotalTokens( int totalTokens ) {
      this.totalTokens = totalTokens;
   }

   @JsonProperty("completion_tokens")
   private int completionTokens;

   @JsonProperty("total_tokens")
   private int totalTokens;

   // Getters and setters
}
