package com.letter_guide.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.letter_guide.config.TestDatabaseConfig;
import com.letter_guide.models.SummarizedDocument;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import({ TestDatabaseConfig.class}) // Import your MongoDB configuration if needed
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SummariesRepositoryTest {

   @AfterEach
   void tearDown() {
      _summariesRepository.deleteAll();
   }

   @Autowired
   private SummariesRepository _summariesRepository;

   @Test
   public void testFindByUserId() {
      // Arrange
      SummarizedDocument summarizedDocument = new SummarizedDocument();
      summarizedDocument.setCategory("Tax filing");
      summarizedDocument.setTasks(List.of("Test21"));
      summarizedDocument.setNextSteps(List.of("Test1"));
      summarizedDocument.setDeadlines(List.of("test1"));
      summarizedDocument.setPriority("high");
      summarizedDocument.setUserId("1");
      summarizedDocument.setSummary("Test User");

      _summariesRepository.save(summarizedDocument);

      // Act
      List<SummarizedDocument> foundSummarizedDocument = _summariesRepository.findByUserId("1");

      // Assert
      assertThat(foundSummarizedDocument.get(foundSummarizedDocument.size()-1)).usingRecursiveComparison().isEqualTo(summarizedDocument);
   }
}
