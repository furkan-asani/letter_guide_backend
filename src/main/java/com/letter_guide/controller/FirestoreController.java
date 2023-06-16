package com.letter_guide.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.letter_guide.models.SummarizedDocument;


@RestController
public class FirestoreController {

   private final Firestore firestore;

   @Autowired
   public FirestoreController(Firestore firestore) {
      this.firestore = firestore;
   }

   @GetMapping("/data")
   public List<SummarizedDocument> getData() throws ExecutionException, InterruptedException {
      ApiFuture<QuerySnapshot> query = firestore.collection("users").get();
      QuerySnapshot querySnapshot = query.get();

      return querySnapshot.getDocuments().stream().map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(SummarizedDocument.class)).toList();
   }
}

