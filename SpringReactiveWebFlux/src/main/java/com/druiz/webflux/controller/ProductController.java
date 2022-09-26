package com.druiz.webflux.controller;

import com.druiz.webflux.model.Product;
import com.druiz.webflux.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //NO ESTOY PRODUCIENDO JSON ESTOY PRODUCIENDO EVENTOS
    public Flux<Product> getAllProducts(){
        return productService.getAllProducts();
    }

}
