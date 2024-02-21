package com.nihat.springwebfluxdemo.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
@EnableReactiveMongoAuditing // This annotation is used to enable auditing in MongoDB. (For createdDate and lastModifiedDate fields.)
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    // get the credentials from .env file
    private final Dotenv dotenv = Dotenv.configure().load();

    // name of the database
    @Override
    protected String getDatabaseName() {
        return dotenv.get("DB_NAME");
    }

    // Database URL
    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create("mongodb://" +
                dotenv.get("DB_USERNAME") + ":" +
                dotenv.get("DB_PASSWORD") + "@" +
                dotenv.get("DB_HOST") + ":" +
                dotenv.get("DB_PORT") + "/" +
                dotenv.get("DB_NAME") +
                "?authSource=admin"); // authSource is the name of the database where the user is defined. Used to check if our user exists.
    }

    // For flexible queries we will use mongoTemplate
    @Bean
    public ReactiveMongoTemplate mongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }
}
