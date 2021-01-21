package bo.juanjose.catalog.controller;

import bo.juanjose.catalog.dto.ProductDto;
import bo.juanjose.catalog.entity.Brand;
import bo.juanjose.catalog.entity.Product;
import bo.juanjose.catalog.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    public final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/anonymous")
    public ResponseEntity<PageImpl<ProductDto>> listProduct(Pageable pageable){
        Page<Product> products = productService.listAllProduct(pageable);
        Page<ProductDto> productsDto = products.map(this::convertToDto);
        return ResponseEntity.ok(new PageImpl<>(productsDto));
    }

    @GetMapping("/anonymous/listBrand")
    public ResponseEntity<List<ProductDto>> listProduct(@RequestParam(name = "brandId", required = false) Long brandId){
        List<Product> products = productService.findByBrand(Brand.builder().id(brandId).build());
        List<ProductDto> productsDto = products.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(productsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
        return productService.getProduct(id)
                .map(product -> ResponseEntity.ok(convertToDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/anonymous/{id}")
    public ResponseEntity<ProductDto> getProductAnonymous(@PathVariable("id") Long id){
        return productService.updateVisits(id)
                .map(product -> ResponseEntity.ok(convertToDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto, BindingResult result) throws ParseException {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product productCreate = productService.createProduct(convertToEntity(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(productCreate));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) throws ParseException {
        productDto.setId(id);
        return productService.updateProduct(convertToEntity(productDto))
                .map(product -> ResponseEntity.ok(convertToDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id)
                .map(product -> ResponseEntity.ok(convertToDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    private String formatMessage( BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto  = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) throws ParseException {
        Product product = modelMapper.map(productDto, Product.class);
        return product;
    }
}
