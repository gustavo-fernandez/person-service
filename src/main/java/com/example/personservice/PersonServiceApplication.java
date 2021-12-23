package com.example.personservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PersonServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PersonServiceApplication.class, args);
  }

}
