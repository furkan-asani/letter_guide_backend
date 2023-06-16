package com.letter_guide.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class OpenAILLMController {

   @GetMapping("/openai")
   public ResponseEntity<String> openAiRequest() {
      return ResponseEntity.ok("Passt alles");
   }
}
