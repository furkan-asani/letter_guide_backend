package com.letter_guide.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "summaries")
public class SummarizedDocument {

   @Id
   private String       id;
   @Field("userId")
   private String       userId;
   private String       summary;
   private String       category;
   private List<String> tasks;
   private List<String> nextSteps;
   private List<String> deadlines;
   private String       priority;

   public String getCategory() {
      return category;
   }

   public List<String> getDeadlines() {
      return deadlines;
   }

   public List<String> getNextSteps() {
      return nextSteps;
   }

   public String getPriority() {
      return priority;
   }

   public String getSummary() {
      return summary;
   }

   public List<String> getTasks() {
      return tasks;
   }

   public String getUserId() {
      return userId;
   }

   public void setCategory( String category ) {
      this.category = category;
   }

   public void setDeadlines( List<String> deadlines ) {
      this.deadlines = deadlines;
   }

   public void setNextSteps( List<String> nextSteps ) {
      this.nextSteps = nextSteps;
   }

   public void setPriority( String priority ) {
      this.priority = priority;
   }

   public void setSummary( String summary ) {
      this.summary = summary;
   }

   public void setTasks( List<String> tasks ) {
      this.tasks = tasks;
   }

   public void setUserId( String userId ) {
      this.userId = userId;
   }
}
