package com.example.personservice;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
public class PersonRepository {

  private static final List<Person> PERSON_TABLE;

  static {
    PERSON_TABLE = new ArrayList<>();

    PERSON_TABLE.add(new Person(1L, "Pepito"));
    PERSON_TABLE.add(new Person(2L, "Juan"));
    PERSON_TABLE.add(new Person(3L, "Pedro"));
  }

  public Mono<Person> findById(Long id) {
    return Flux.fromIterable(PERSON_TABLE)
      .filter(p -> p.getId().equals(id))
      .next();
  }

  public Flux<Person> findAll() {
    return Flux.fromIterable(PERSON_TABLE);
  }

  public Mono<Boolean> insert(Person p) {
    return Mono.just(p) // Mono<1, Pepito>
      .doOnSubscribe(subscription -> log.info("Se da inicio al insert"))
      .filter(newP -> !PERSON_TABLE.contains(newP)) // Mono<Empty>
      .map(newP -> PERSON_TABLE.add(newP)) // Mono<true>
      .switchIfEmpty(Mono.just(false)); // Mono<false>
  }

  public Mono<Boolean> update(Person p) {
    return Mono.just(p)
      .filter(currentP -> PERSON_TABLE.contains(currentP)) // Mono<Empty>
      .map(currentP -> {
        int index = PERSON_TABLE.indexOf(currentP);
        PERSON_TABLE.set(index, currentP);
        return true;
      }) // Mono<true>
      .defaultIfEmpty(false); // Mono<false>
  }

}
