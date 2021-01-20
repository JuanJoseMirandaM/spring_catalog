package bo.juanjose.catalog.service.impl;

import bo.juanjose.catalog.entity.Brand;
import bo.juanjose.catalog.entity.Product;
import bo.juanjose.catalog.entity.User;
import bo.juanjose.catalog.repository.ProductRepository;
import bo.juanjose.catalog.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
//    @Autowired
    private final ProductRepository productRepository;

    @Override
    public Page<Product> listAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findByBrand(Brand brand) {
        return productRepository.findAllByBrand(brand);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setStatus("CREATED");
        product.setUser(User.builder().id(1L).build());

        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        Optional<Product> productDB = getProduct(product.getId());
        return productDB.map(productdb -> {
            productdb.setName(product.getName());
            productdb.setDescription(product.getDescription());
            productdb.setPrice(product.getPrice());
            productdb.setBrand(product.getBrand());

            productdb.setUpdatedAt(new Date());
            productdb.setUser(User.builder().id(1L).build());

            return productRepository.save(productdb);
        });
    }

    @Override
    public Optional<Product> deleteProduct(Long id) {
        Optional<Product> productDeleted = getProduct(id);
        return productDeleted.map(product -> {
            product.setStatus("DELETED");
            product.setUpdatedAt(new Date());
            product.setUser(User.builder().id(1L).build());

            return productRepository.save(product);
        });
    }

    @Override
    public Optional<Product> updateStock(Long id, Double quantity) {
        Optional<Product> productDB = getProduct(id);
        return productDB.map(product -> {
           product.setStock(product.getStock()+quantity);

           product.setUpdatedAt(new Date());
           product.setUser(User.builder().id(1L).build());

           return productRepository.save(product);
        });
    }
}
