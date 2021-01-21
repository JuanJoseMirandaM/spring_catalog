package bo.juanjose.catalog.controller;


import bo.juanjose.catalog.dto.AuthenticationRequest;
import bo.juanjose.catalog.dto.AuthenticationResponse;
import bo.juanjose.catalog.security.jwt.JWTUtil;
import bo.juanjose.catalog.security.service.MyUserDetailsImpl;
import bo.juanjose.catalog.security.service.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            MyUserDetailsImpl userDetails = myUserDetailsServiceImpl.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), jwt), HttpStatus.OK);
        }catch (BadCredentialsException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
