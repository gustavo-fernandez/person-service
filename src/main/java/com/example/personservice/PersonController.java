package com.example.personservice;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonController {

  private final PersonRepository personRepository;

  @GetMapping("/api/person")
  public Flux<Person> findAll() {
    return personRepository.findAll();
  }

  @GetMapping("/api/person/{id}")
  public Mono<Person> findById(@PathVariable Long id) {
    return personRepository.findById(id).delayElement(Duration.ofSeconds(3));
  }

  @PostMapping("/api/person")
  public Mono<Boolean> saveOrUpdate(@RequestBody Person personParam) {
    return personRepository.findById(personParam.getId()) // Existe -> Mono<Person> | No Existe -> Mono<Empty>
      .flatMap(personaEncontrada -> {
        log.info("La persona fue encontrada, se procede con el update");
        return personRepository.update(personParam);
      }) // Mono<true>
      .switchIfEmpty(personRepository.insert(personParam)); // Mono<true>
  }

  /*private static void beberAgua(Mono<Boolean> insert) {
    System.out.println("ejecutando metodo beber agua");
  }

  public static void main(String[] args) {
    PersonRepository personRepository = new PersonRepository();
    beberAgua(personRepository.insert(new Person()));
  }*/

}
