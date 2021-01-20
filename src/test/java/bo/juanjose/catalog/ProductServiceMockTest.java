package bo.juanjose.catalog;

import bo.juanjose.catalog.entity.Brand;
import bo.juanjose.catalog.entity.Product;
import bo.juanjose.catalog.entity.User;
import bo.juanjose.catalog.repository.ProductRepository;
import bo.juanjose.catalog.service.ProductService;
import bo.juanjose.catalog.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .brand(Brand.builder().id(3L).build())
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1230.99"))
                .status("CREATED")
                .user(User.builder().id(1L).build()).build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer))
                .thenReturn(computer);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct(1L).orElse(null);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidUpdateStock_thenReturnNewStock(){
        Product newStock = productService.updateStock(1L, Double.parseDouble("8")).orElse(null);
        Assertions.assertThat(newStock.getStock()).isEqualTo(18);
    }

    @Test
    public void whenValidDeleted_thenReturnDeleted(){
        Product deleted = productService.deleteProduct(1L).orElse(null);
        Assertions.assertThat(deleted.getStatus()).isEqualTo("DELETED");
    }
}
