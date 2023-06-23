package com.letter_guide.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


@Configuration
@PropertySource("classpath:secrets.properties")
public class TestDatabaseConfig extends AbstractMongoClientConfiguration {

   @Value("${spring.data.mongodb.uri.test}")
   private String mongoDbUri;

   @Override
   protected String getDatabaseName() {
      return "letter_guide";
   }

   @Override
   public MongoClient mongoClient() {
      ConnectionString connectionString = new ConnectionString(mongoDbUri);
      MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();

      return MongoClients.create(mongoClientSettings);
   }

   @Bean
   public MongoTemplate mongoTemplate() {
      return new MongoTemplate(mongoClient(), getDatabaseName());
   }
}
