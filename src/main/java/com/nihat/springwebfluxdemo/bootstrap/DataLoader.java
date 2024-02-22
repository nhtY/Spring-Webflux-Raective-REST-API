package com.nihat.springwebfluxdemo.bootstrap;

import com.nihat.springwebfluxdemo.domain.Product;
import com.nihat.springwebfluxdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        productRepository.deleteAll()
                .then(productRepository.count())
                .subscribe(count -> {
                    if (count == 0) {
                        loadProducts();
                    }
                });
    }

    private void loadProducts() {
        System.out.println("Loading products...");

        Product p1 = Product.builder()
                .name("Iphone 12")
                .description("Apple iPhone 12, 128GB, Black - Fully Unlocked")
                .imgUrl("https://m.media-amazon.com/images/I/71eTwPQolnL._AC_SL1500_.jpg")
                .price(BigDecimal.valueOf(28999.99))
                .build();

        Product p2 = Product.builder()
                .name("iPhone 12 Pro Max")
                .description("Apple iPhone 12 Pro Max, 128GB, Pacific Blue - Fully Unlocked")
                .imgUrl("https://m.media-amazon.com/images/I/41CTA3XLpEL._AC_SL1200_.jpg")
                .price(BigDecimal.valueOf(28999.99))
                .build();


        Product p3 = Product.builder()
                .name("Samsung Galaxy S24 Ultra")
                .description("Samsung Galaxy S24 Ultra 5G, 128GB, Titanium Purple - Fully Unlocked")
                .imgUrl("https://m.media-amazon.com/images/I/71F0gwNooIL._AC_SL1500_.jpg")
                .price(BigDecimal.valueOf(69466.99))
                .build();

        Product p4 = Product.builder()
                .name("Huawei Watch GT2")
                .description("Huawei Watch GT2 Pro, Night Black, 2 Week Battery Life, GPS, 100+ Workout Modes, SpO2, 1.39\" AMOLED Touch Screen, Wireless Charging")
                .imgUrl("https://m.media-amazon.com/images/I/71f-m4vr8wL._AC_SL1500_.jpg")
                .price(BigDecimal.valueOf(1087.54))
                .build();

        productRepository.saveAll(Flux.just(p1, p2, p3, p4))
                .subscribe(product -> System.out.println("Product saved: " + product.getName()));
    }
}
