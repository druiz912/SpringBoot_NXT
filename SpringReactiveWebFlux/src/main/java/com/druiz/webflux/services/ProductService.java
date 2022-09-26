package com.druiz.webflux.services;

import com.druiz.webflux.model.Product;
import com.druiz.webflux.repo.ProductRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //Devolver todos los productos de la bbdd --> trabajamos con el stream events
    public Flux<Product> getAllProducts() {
        //Flux crea eventos
        return productRepo.findAll()
                .delayElements(Duration.ofSeconds(10)); // Simulamos un atraso en la respuesta para ver mas claro lo de reactivo
    }
}


