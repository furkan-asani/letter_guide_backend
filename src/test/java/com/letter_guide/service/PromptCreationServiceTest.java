package com.letter_guide.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class PromptCreationServiceTest {

   private final PromptCreationService _promptCreationService = new PromptCreationService();


   @Test
   void testGeneratePrompt() {
      //given
      String extractedText = "This is a sample text which should be analyzed and summarized";

      //when
      String prompt = _promptCreationService.createPrompt(extractedText);

      //then
      assertThat(prompt).isEqualTo("I want you to act as an expert translator and summarizer. I will provide you with extracted text from a german letter containing hard to understand 'government talk' and you will categorize it, summarize it, extract possible tasks or next steps if there are any, extract possible deadlines if there are any and you will decide on the priority and importance of this letter. You will provide this response in a json format which will look like this:\n"
            + "{\n" + "  category,\n" + "  summary,\n" + "  tasks,\n" + "  nextSteps,\n" + "  deadlines,\n" + "  priority\n" + "}\n"
            + "The priority can be either low, medium or high. Please provide the date in the following format: 'dd-MM-YYY'. Tasks and next steps should be arrays with the respective tasks in the correct order. Summary and category can be simple strings.\n"
            + "The text you should process: \n" + "\n" + "This is a sample text which should be analyzed and summarized");
   }
}