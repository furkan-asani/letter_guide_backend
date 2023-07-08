package com.letter_guide.service;

import org.springframework.stereotype.Service;


@Service
public class PromptCreationService {

   public String createPrompt( String extractedText ) {
      String basicPrompt = """
            "I want you to act as an expert translator and summarizer. Your task is to process a German letter written in complex 'government talk'. You will translate it, categorize it, summarize it, extract any tasks or next steps, identify any deadlines, and determine the priority of the letter.
            Please provide your response in the following JSON format:

            {
              "category": "",
              "summary": "",
              "tasks": [],
              "nextSteps": [],
              "deadlines": "",
              "priority": ""
            }
            The priority should be either 'low', 'medium', or 'high'. Dates should be formatted as 'dd-MM-YYYY'. tasks and nextSteps should be arrays with the tasks in the correct order. summary and category should be simple strings.

            Please provide your response in German.

            Here is the text you should process: \n
            """;

      return basicPrompt + extractedText;
   }
}
