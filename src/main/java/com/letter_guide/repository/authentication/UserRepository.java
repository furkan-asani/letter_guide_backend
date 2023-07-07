package com.letter_guide.repository.authentication;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.letter_guide.models.authentication.User;


public interface UserRepository extends MongoRepository<User, Long> {

   User findByEmail( String email );
}

