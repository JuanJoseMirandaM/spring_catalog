package bo.juanjose.catalog.service;

import bo.juanjose.catalog.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BrandService {
    public Page<Brand> listAllBrand(Pageable pageable);
    public Optional<Brand> getBrand(Long id);
    public Brand createBrand(Brand brand);
    public Optional<Brand> updateBrand(Brand brand);
    public Optional<Brand> deleteBrand(Long id);
}
