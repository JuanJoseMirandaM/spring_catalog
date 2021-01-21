package bo.juanjose.catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto {
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;

    @NotEmpty(message = "El username no debe ser vacio")
    private String username;

    @NotEmpty(message = "El email no debe ser vacio")
    @Size(max = 50, message = "El email es demasiado largo")
    @Email(message = "Debe ser un email valido")
    private String email;

    @NotEmpty(message = "El password no debe ser vacio")
    private String password;
    
    private String status;
}
