package com.letter_guide.models.api;

public class CreateSummarizedDocumentRequest {
   private String extractedText;

   public String getExtractedText() {
      return extractedText;
   }

   public void setExtractedText(String extractedText) {
      this.extractedText = extractedText;
   }
}
