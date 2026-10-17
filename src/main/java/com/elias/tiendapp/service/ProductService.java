package com.elias.tiendapp.service;

import com.elias.tiendapp.models.Product;
import com.elias.tiendapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        if(productRepository.findByBarCode(product.getBarCode()).isPresent()){
            throw new IllegalArgumentException("El codigo de barras " + product.getBarCode() + " ya está asignado a otro producto");
        }
        return productRepository.save(product);

    }
    public Optional<Product> findByBarCode(String barCode) {
        return productRepository.findByBarCode(barCode);
    }

    public Product updateProduct(long id, Product updateData) {
        Product existingProduct = getProductById(id);
        Optional<Product> sameBarCode = productRepository.findByBarCode(updateData.getBarCode());

        if (sameBarCode.isPresent() && !sameBarCode.get().getId().equals(id)) {
            throw new RuntimeException("Ya existe otro producto con el código de barras: " + updateData.getBarCode());
        }
        existingProduct.setName(updateData.getName());
        existingProduct.setDescription(updateData.getDescription());
        existingProduct.setBarCode(updateData.getBarCode());
        existingProduct.setPrice(updateData.getPrice());
        existingProduct.setUnit(updateData.getUnit());
        existingProduct.setCategory(updateData.getCategory());

        return productRepository.save(existingProduct);


    }

    public void deleteProduct(long id) {
        Product existingProduct = getProductById(id);
        productRepository.delete(existingProduct);
    }

    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElseThrow( () -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
}
