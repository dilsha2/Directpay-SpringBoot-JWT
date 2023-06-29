package lk.directpay.company.controller;

import lk.directpay.company.auth.AuthenticationRequest;
import lk.directpay.company.auth.AuthenticationResponse;
import lk.directpay.company.auth.RegisterRequest;
import lk.directpay.company.exception.InvalidRequestException;
import lk.directpay.company.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request){
        try {
            return ResponseEntity.ok(service.register(request));
        }catch(InvalidRequestException e){
            return ResponseEntity.badRequest().body(new AuthenticationResponse(e.getMessage()));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
        try {
            return ResponseEntity.ok(service.authenticate(request));
        }catch(InvalidRequestException e){
            return ResponseEntity.badRequest().body(new AuthenticationResponse(e.getMessage()));
        }
    }
}
