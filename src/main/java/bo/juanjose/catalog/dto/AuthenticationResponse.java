package bo.juanjose.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data @AllArgsConstructor @NonNull
public class AuthenticationResponse {
    private Long id;
    private String username;
    private String email;
    private String jwt;
}
