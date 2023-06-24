package com.letter_guide.models.api.openAI.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Choice {

   private int     index;
   private Message message;

   @JsonProperty("finish_reason")
   private String finishReason;

   public int getIndex() {
      return index;
   }

   public void setIndex( int index ) {
      this.index = index;
   }

   public Message getMessage() {
      return message;
   }

   public void setMessage( Message message ) {
      this.message = message;
   }

   public String getFinishReason() {
      return finishReason;
   }

   public void setFinishReason( String finishReason ) {
      this.finishReason = finishReason;
   }
   // Getters and setters
}
