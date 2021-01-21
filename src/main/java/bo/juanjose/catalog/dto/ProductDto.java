package bo.juanjose.catalog.dto;

import bo.juanjose.catalog.entity.Brand;
import bo.juanjose.catalog.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductDto {
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;
    private String description;

    @Positive(message = "El stock debe ser mayor que cero")
    private Double stock;
    private Double price;

    private String status;

    @NotNull(message = "La marca no puede ser nula")
    private BrandDto brand;

    private int visits;
}
