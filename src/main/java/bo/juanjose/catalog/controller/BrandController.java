package bo.juanjose.catalog.controller;

import bo.juanjose.catalog.dto.BrandDto;
import bo.juanjose.catalog.entity.Brand;
import bo.juanjose.catalog.service.BrandService;
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
@RequestMapping(value = "/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    public final ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<PageImpl<BrandDto>> listBrand(Pageable pageable){
        Page<Brand> brands = brandService.listAllBrand(pageable);
        Page<BrandDto> brandsDto = brands.map(this::convertToDto);
        return ResponseEntity.ok(new PageImpl<>(brandsDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrand(@PathVariable("id") Long id){
        return brandService.getBrand(id)
                .map(brand -> ResponseEntity.ok(convertToDto(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody BrandDto brandDto, BindingResult result) throws ParseException {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Brand brandCreate = brandService.createBrand(convertToEntity(brandDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(brandCreate));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BrandDto> updateBrand(@PathVariable("id") Long id, @RequestBody BrandDto brandDto) throws ParseException {
        brandDto.setId(id);
        return brandService.updateBrand(convertToEntity(brandDto))
                .map(brand -> ResponseEntity.ok(convertToDto(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BrandDto> deleteBrand(@PathVariable("id") Long id){
        return brandService.deleteBrand(id)
                .map(brand -> ResponseEntity.ok(convertToDto(brand)))
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

    private BrandDto convertToDto(Brand brand) {
        BrandDto brandDto  = modelMapper.map(brand, BrandDto.class);
        return brandDto;
    }

    private Brand convertToEntity(BrandDto brandDto) throws ParseException {
        Brand brand = modelMapper.map(brandDto, Brand.class);
        return brand;
    }
}
