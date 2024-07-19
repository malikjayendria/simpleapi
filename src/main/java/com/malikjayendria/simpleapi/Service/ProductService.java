package com.malikjayendria.simpleapi.Service;

import com.malikjayendria.simpleapi.Entity.Product;
import com.malikjayendria.simpleapi.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Product entities.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Save a product.
     *
     * @param product the entity to save
     * @return the persisted entity
     */
    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(newProduct);
    }


    /**
     * Get all the products.
     *
     * @return the list of entities
     */
    public ResponseEntity<List<Product> > fetchAllProducts()
    {
        return ResponseEntity.ok(productRepository.findAll());
    }


    /**
     * Get one product by ID.
     *
     * @param id the ID of the entity
     * @return the entity
     */
    public ResponseEntity<Optional<Product> >
    fetchProductById(Long id)
    {
        Optional<Product> product
                = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update a product.
     *
     * @param id the ID of the entity
     * @param updatedProduct the updated entity
     * @return the updated entity
     */
    public ResponseEntity<Product> updateProduct(Long id, Product updatedProduct)
    {
        if (id == null) {
            throw new IllegalArgumentException(
                    "ID cannot be null");
        }
        Product Existingproduct
                = productRepository.findById(id).orElseThrow(
                ()
                        -> new EntityNotFoundException(
                        String.valueOf(id)));
        Existingproduct.setName(updatedProduct.getName());
        Existingproduct.setPrice(updatedProduct.getPrice());
        Existingproduct.setQuantity(
                updatedProduct.getQuantity());
        Product savedEntity
                = productRepository.save(Existingproduct);
        return ResponseEntity.ok(savedEntity);
    }

    /**
     * Delete the product by ID.
     *
     * @param id the ID of the entity
     */
    public ResponseEntity<String> deleteProduct(Long id)
    {
        productRepository.deleteById(id);
        return ResponseEntity.ok(
                "Product Deleted Successfully");
    }
}
