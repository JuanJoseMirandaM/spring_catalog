package bo.juanjose.catalog.repository;

import bo.juanjose.catalog.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    public Page<Brand> findAll(Pageable pageable);
}
