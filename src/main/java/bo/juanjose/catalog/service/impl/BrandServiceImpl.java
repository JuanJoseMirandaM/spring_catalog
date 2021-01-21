package bo.juanjose.catalog.service.impl;

import bo.juanjose.catalog.entity.Brand;
import bo.juanjose.catalog.entity.User;
import bo.juanjose.catalog.repository.BrandRepository;
import bo.juanjose.catalog.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Page<Brand> listAllBrand(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Override
    public Optional<Brand> getBrand(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand createBrand(Brand brand) {
        brand.setCreatedAt(new Date());
        brand.setUpdatedAt(new Date());
        brand.setStatus("CREATED");
        brand.setUser(User.builder().id(1L).build());

        return brandRepository.save(brand);
    }

    @Override
    public Optional<Brand> updateBrand(Brand brand) {
        Optional<Brand> brandDB = getBrand(brand.getId());
        return brandDB.map(branddb -> {
            branddb.setName(brand.getName());

            branddb.setUpdatedAt(new Date());
            branddb.setUser(User.builder().id(1L).build());

            return brandRepository.save(branddb);
        });
    }

    @Override
    public Optional<Brand> deleteBrand(Long id) {
        Optional<Brand> brandDeleted = getBrand(id);
        return brandDeleted.map(brand -> {
            brand.setStatus("DELETED");
            brand.setUpdatedAt(new Date());
            brand.setUser(User.builder().id(1L).build());

            return brandRepository.save(brand);
        });
    }
}
