package bo.juanjose.catalog.repository;

import bo.juanjose.catalog.entity.Category;
import bo.juanjose.catalog.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Page<Product> findAll(Pageable pageable);

    public List<Product> findByCategory(Category category);
}
