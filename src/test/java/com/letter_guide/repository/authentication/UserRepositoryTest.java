package com.letter_guide.repository.authentication;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.letter_guide.config.TestDatabaseConfig;
import com.letter_guide.models.authentication.User;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import({ TestDatabaseConfig.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class UserRepositoryTest {

   @Autowired
   private UserRepository _userRepository;
   private User           _user;

   @BeforeEach
   void setUp() {
      _user = new User();
      String mail = "example@gmail.com";
      _user.setEmail(mail);
      _user.setPassword("samplePassword");
      _user.setId(new Date().getTime());
      _userRepository.save(_user);
   }

   @AfterEach
   void tearDown() {
      _userRepository.delete(_user);
   }

   @Test
   void testFindUserByEmail() {
      //given
      String email = "example@gmail.com";

      //when
      User user = _userRepository.findByEmail(email);

      //then
      assertThat(user).isNotNull();
      assertThat(user.getEmail()).isEqualTo(email);
   }
}