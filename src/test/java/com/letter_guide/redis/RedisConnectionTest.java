package com.letter_guide.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisConnectionTest {

   @Autowired
   private StringRedisTemplate template;

   @Test
   public void testRedisConnection() {
      ValueOperations<String, String> ops = this.template.opsForValue();
      String key = "spring.boot.redis.test";
      String value = "testValue";

      // Ensure the key is clear
      ops.getOperations().delete(key);

      // Save the key
      ops.set(key, value);

      // Fetch the key
      String fetchedValue = ops.get(key);

      // Assert the fetched value is equal to the saved one
      assertEquals(value, fetchedValue);

      // Clean up
      ops.getOperations().delete(key);
   }
}
