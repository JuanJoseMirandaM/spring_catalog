package bo.juanjose.catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BrandDto {
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;

    private String status;
}
