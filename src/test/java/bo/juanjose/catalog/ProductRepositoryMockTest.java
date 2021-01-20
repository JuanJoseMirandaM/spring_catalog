package bo.juanjose.catalog;

import bo.juanjose.catalog.entity.Brand;
import bo.juanjose.catalog.entity.Product;
import bo.juanjose.catalog.entity.User;
import bo.juanjose.catalog.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product01 = Product.builder()
                .name("computer")
                .brand(Brand.builder().id(3L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1230.99"))
                .status("CREATED")
                .createdAt(new Date())
                .updatedAt(new Date())
                .user(User.builder().id(1L).build()).build();
        productRepository.save(product01);

        List<Product> founds = productRepository.findAllByBrand(product01.getBrand());

        Assertions.assertThat(founds.size()).isEqualTo(2);
    }
}
