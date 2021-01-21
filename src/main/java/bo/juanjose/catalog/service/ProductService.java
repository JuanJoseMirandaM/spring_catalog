package bo.juanjose.catalog.service;

import bo.juanjose.catalog.entity.Brand;
import bo.juanjose.catalog.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Page<Product> listAllProduct(Pageable pageable);
    public List<Product> findByBrand(Brand brand);
    public Optional<Product> getProduct(Long id);
    public Product createProduct(Product product);
    public Optional<Product> updateProduct(Product product);
    public Optional<Product> deleteProduct(Long id);

    public Optional<Product> updateStock(Long id, Double quantity);

    public Optional<Product> updateVisits(Long id);
}
