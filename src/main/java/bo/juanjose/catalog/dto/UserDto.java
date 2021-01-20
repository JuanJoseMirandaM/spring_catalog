package bo.juanjose.catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto {
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;

    @NotEmpty(message = "El email no debe ser vacio")
    private String email;

    @NotEmpty(message = "El password no debe ser vacio")
    private String password;
    
    private String status;
}
