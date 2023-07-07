package com.letter_guide.models.authentication;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document(collection = "users")
public class User {

   @Id
   private long id;

   private String email;

   private String password;

}
