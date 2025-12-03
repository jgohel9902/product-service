package com.example.product_service.controller;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    // GET ALL PRODUCTS
    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    // CREATE PRODUCT
    @PostMapping
    public Product create(@RequestBody Product product) {
        return repo.save(product);
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product p) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setTitle(p.getTitle());
                    existing.setDescription(p.getDescription());
                    existing.setPrice(p.getPrice());
                    existing.setSku(p.getSku());
                    existing.setStock(p.getStock());
                    existing.setImage(p.getImage());
                    return repo.save(existing);
                }).orElse(null);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
