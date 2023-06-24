package com.letter_guide.models.debug;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rawResponses")
public class RawResponse {
   @Id
   private String id;
   private String rawJson;

   public String getId() {
      return id;
   }

   public void setId( String id ) {
      this.id = id;
   }

   public String getRawJson() {
      return rawJson;
   }

   public void setRawJson( String rawJson ) {
      this.rawJson = rawJson;
   }
}
