package com.letter_guide.aspect;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;


@Aspect
@Component
public class RateLimitAspect {

   @Autowired
   private HttpServletRequest request;

   @Autowired
   private RedisTemplate<String, String> redisTemplate;

   @Before("@annotation(com.letter_guide.aspect.RateLimit)")
   public void rateLimit() throws RateLimitException {
      String ip = request.getRemoteAddr();
      ValueOperations<String, String> ops = redisTemplate.opsForValue();

      long count = ops.increment(ip, 1);
      if (count == 1) {
         redisTemplate.expire(ip, 1, TimeUnit.DAYS);
      }

      if (count > 3) {
         throw new RateLimitException("Too many requests");
      }
   }

   public static class RateLimitException extends Throwable {

      public RateLimitException( String message ) {
         super(message);
      }
   }
}
