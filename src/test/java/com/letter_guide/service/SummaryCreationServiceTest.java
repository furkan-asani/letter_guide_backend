package com.letter_guide.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


class SummaryCreationServiceTest {
   @InjectMocks
   private final SummaryCreationService _summaryCreationService = new SummaryCreationService();

   @Mock
   private PromptCreationService _promptCreationService;

   @Test
   void testCreateRequestJson() {
      //given
      MockitoAnnotations.openMocks(this);
      Mockito.when(_promptCreationService.createPrompt(Mockito.anyString())).thenCallRealMethod();

      //when
      String apiRequest = _summaryCreationService.createOpenAIApiRequest(
            "You will have to pay your rent in 2 months or we will have to kick you out respectfully");

      //then
      assertThat(apiRequest).isNotBlank();
      String expectedString = """
            {"model":"gpt-3.5-turbo","messages":[{"role":"system","content":"I want you to act as an expert translator and summarizer. I will provide you with extracted text from a german letter containing hard to understand 'government talk' and you will categorize it, summarize it, extract possible tasks or next steps if there are any, extract possible deadlines if there are any and you will decide on the priority and importance of this letter. You will provide this response in a json format which will look like this:\\n{\\n  category,\\n  summary,\\n  tasks,\\n  nextSteps,\\n  deadlines,\\n  priority\\n}\\nThe priority can be either low, medium or high. Please provide the date in the following format: 'dd-MM-YYY'. Tasks and next steps should be arrays with the respective tasks in the correct order. Summary and category can be simple strings.\\nThe text you should process: \\n\\nYou will have to pay your rent in 2 months or we will have to kick you out respectfully"}],"temperature":0.2}""";
      assertThat(apiRequest).isEqualTo(expectedString);
   }
}
