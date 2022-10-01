package com.druiz.bosonit.reactive.repo;

import com.druiz.bosonit.reactive.documents.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepo extends ReactiveMongoRepository<Person, Integer> {

}
