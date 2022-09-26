package com.druiz.webflux.repo;

import com.druiz.webflux.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepo extends ReactiveCrudRepository<Product,Integer> {

    //ReactiveCrudRepository extiende de Repository
    // y va en paralelo completamente con el CrudRepository normal y corriente.
    // Es una implementación totalmente directamente

}
