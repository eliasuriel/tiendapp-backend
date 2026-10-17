package com.elias.tiendapp.repository;

import com.elias.tiendapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByBarCode(String barCode);

}
