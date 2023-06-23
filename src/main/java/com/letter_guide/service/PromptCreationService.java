package com.letter_guide.service;

import org.springframework.stereotype.Service;


@Service
public class PromptCreationService {

   private final String baiscPrompt = """
I want you to act as an expert translator and summarizer. I will provide you with extracted text from a german letter containing hard to understand 'government talk' and you will categorize it, summarize it, extract possible tasks or next steps if there are any, extract possible deadlines if there are any and you will decide on the priority and importance of this letter. You will provide this response in a json format which will look like this:
{
  category,
  summary,
  tasks,
  nextSteps,
  deadlines,
  priority
}
The priority can be either low, medium or high. Please provide the date in the following format: 'dd-MM-YYY'. Tasks and next steps should be arrays with the respective tasks in the correct order. Summary and category can be simple strings.
The text you should process: \n
""";

   public String createPrompt( String extractedText ) {
      return baiscPrompt + extractedText;
   }
}
