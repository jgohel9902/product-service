package com.example.product_service;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(ProductRepository repo) {
        return args -> {
            if (repo.count() == 0) {

                repo.save(Product.builder()
                        .title("Wireless Headphones")
                        .description("Noise-cancelling over-ear headphones")
                        .price(149.99f)
                        .sku("HD-1001")
                        .image("https://via.placeholder.com/300x200")
                        .stock(10)
                        .build()
                );

                repo.save(Product.builder()
                        .title("Mechanical Keyboard")
                        .description("RGB backlit mechanical keyboard")
                        .price(89.99f)
                        .sku("KB-500")
                        .image("https://via.placeholder.com/300x200")
                        .stock(5)
                        .build()
                );
            }
        };
    }
}
