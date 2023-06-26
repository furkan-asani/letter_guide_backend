package com.letter_guide.models.api.openAI.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

   private String role;
   private String content;

   public String getContent() {
      return content;
   }

   public String getRole() {
      return role;
   }

   public void setContent( String content ) {
      this.content = content;
   }

   public void setRole( String role ) {
      this.role = role;
   }
   // Getters and setters
}
