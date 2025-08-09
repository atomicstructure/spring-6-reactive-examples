package com.samantha.springframewwork.repositories;

import com.samantha.springframewwork.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

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

        personMono.map((person -> {
            return person.getLastName();
        })).subscribe(lastName -> {
            System.out.println(lastName);
        });
    }
}