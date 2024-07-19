package com.malikjayendria.simpleapi.Repository;

import com.malikjayendria.simpleapi.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
