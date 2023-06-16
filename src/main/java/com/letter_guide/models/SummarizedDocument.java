package com.letter_guide.models;

import java.util.List;


public class SummarizedDocument {

   private       String       _summary;
   private       String       _category;
   private List<String> _tasks;
   private List<String> _nextSteps;
   private List<String> _deadlines;

   public String getSummary() {
      return _summary;
   }

   public void setSummary( String summary ) {
      _summary = summary;
   }

   public String getCategory() {
      return _category;
   }

   public void setCategory( String category ) {
      _category = category;
   }

   public List<String> getTasks() {
      return _tasks;
   }

   public void setTasks( List<String> tasks ) {
      _tasks = tasks;
   }

   public List<String> getNextSteps() {
      return _nextSteps;
   }

   public void setNextSteps( List<String> nextSteps ) {
      _nextSteps = nextSteps;
   }

   public List<String> getDeadlines() {
      return _deadlines;
   }

   public void setDeadlines( List<String> deadlines ) {
      _deadlines = deadlines;
   }

   public String getPriority() {
      return _priority;
   }

   public void setPriority( String priority ) {
      _priority = priority;
   }

   private String _priority;
}
