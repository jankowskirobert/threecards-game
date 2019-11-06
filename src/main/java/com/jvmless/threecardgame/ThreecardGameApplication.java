package com.jvmless.threecardgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableCassandraRepositories
public class ThreecardGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreecardGameApplication.class, args);
	}

}
