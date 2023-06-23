package com.letter_guide.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "summaries")
public class SummarizedDocument {

   @Id
private String          id;
   @Field("userId")
   private String         userId;
   private       String summary;
   private       String category;
   private List<String> tasks;
   private List<String> nextSteps;
   private List<String> deadlines;
   private String priority;


   public String getSummary() {
      return summary;
   }

   public String getUserId() {
      return userId;
   }

   public void setSummary( String summary ) {
      this.summary = summary;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory( String category ) {
      this.category = category;
   }

   public List<String> getTasks() {
      return tasks;
   }

   public void setTasks( List<String> tasks ) {
      this.tasks = tasks;
   }

   public List<String> getNextSteps() {
      return nextSteps;
   }

   public void setNextSteps( List<String> nextSteps ) {
      this.nextSteps = nextSteps;
   }

   public List<String> getDeadlines() {
      return deadlines;
   }

   public void setDeadlines( List<String> deadlines ) {
      this.deadlines = deadlines;
   }

   public String getPriority() {
      return priority;
   }

   public void setPriority( String priority ) {
      this.priority = priority;
   }

   public void setUserId( String userId ) {
      this.userId = userId;
   }
}
