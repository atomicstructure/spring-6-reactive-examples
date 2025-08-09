package com.samantha.springframewwork.repositories;

import com.samantha.springframewwork.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoriesImpl implements PersonRepository {

    Person michael = Person.builder().id(1).firstName("Oluwafemi").lastName("Ogundare").build();
    Person fiona = Person.builder().id(2).firstName("Olusesi").lastName("Arnold").build();
    Person sam = Person.builder().id(3).firstName("Rebecca").lastName("Kito").build();
    Person jess = Person.builder().id(4).firstName("Jesse").lastName("Arnold").build();


    @Override
    public Mono<Person> getById(Integer id) {
        return findAll().filter(person -> person.getId().equals(id)).next();
    }

    @Override
    public Flux<Person> findAll() {

        return Flux.just(michael, fiona, sam, jess);
    }
}
