/*package com.example.personservice;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class RouterFunctionConfiguration {

  @Bean
  RouterFunction<?> routerFunction(HandlerFunction<ServerResponse> personHandler) {
    if (env.getIsEnglish()) {
      return RouterFunctions.route(
        GET("/api/person/{id}").and(accept(MediaType.APPLICATION_JSON)), personHandler);
    }
    return RouterFunctions.route(
      GET("/servicio").and(accept(MediaType.APPLICATION_JSON)), personHandler)
      .andNest(GET("/persona").and(accept(MediaType.APPLICATION_JSON)), personHandler)
      .andNest(GET("/persona/{id}").and(accept(MediaType.APPLICATION_JSON)), personHandler);
  }

}

@Component
class PersonHandler implements HandlerFunction<ServerResponse> {

  @Override
  public Mono<ServerResponse> handle(ServerRequest request) {
    request.path();
    Long id = Long.valueOf(request.pathVariable("id"));
    Mono<Person> getPersonById = getPersonById(id);
    return ServerResponse.ok().body(getPersonById, Person.class);
  }

  private Mono<Person> getPersonById(Long id) {
    // consulta en BD
    return Mono.just(new Person(id, "Pepito"));
  }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
  private Long id;
  private String name;
}*/
