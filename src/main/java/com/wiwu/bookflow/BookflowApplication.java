package com.wiwu.bookflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
@SpringBootApplication
@EnableKafka
public class BookflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookflowApplication.class, args);
	}

}
