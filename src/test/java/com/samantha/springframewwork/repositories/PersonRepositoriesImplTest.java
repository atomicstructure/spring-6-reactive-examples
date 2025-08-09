package com.samantha.springframewwork.repositories;

import com.samantha.springframewwork.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoriesImplTest {

    PersonRepository personRepository = new PersonRepositoriesImpl();


//    Not the preferred method because it is a Blocking method
    @Test
    void testMonoByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);

        Person person = personMono.block();

        System.out.println(
                person.toString()
        );
    }

    @Test
    void tettGetByIdSubscriber() {
        Mono<Person> personMono = personRepository.getById(1);

        personMono.subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    void testMapOperation() {
        Mono<Person> personMono = personRepository.getById(3);

        personMono.map((Person::getLastName)).subscribe(System.out::println);
    }

    @Test
    void testFluxBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();
                personFlux.map(Person::getFirstName)
                .subscribe(System.out::println);
        // This is a blocking operation, not recommended in reactive programming
        Person person = personFlux.blockFirst();
        System.out.println(person.toString());
    }

    @Test
    void testFluxSubscriber() {
        Flux<Person> personFlux = personRepository.findAll();

        personFlux.subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    void testFluxToList() {
        Flux<Person> personFlux = personRepository.findAll();

        Mono<List<Person>> listMono = personFlux.collectList();
        listMono.subscribe(list -> {
            list.forEach(person -> System.out.println(person.getFirstName()));
        });
    }
}