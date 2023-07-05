package lk.directpay.company.controller;

import lk.directpay.company.auth.AuthenticationRequest;
import lk.directpay.company.auth.AuthenticationResponse;
import lk.directpay.company.auth.ErrorResponse;
import lk.directpay.company.auth.RegisterRequest;
import lk.directpay.company.entities.User;
import lk.directpay.company.repositories.UserRepository;
import lk.directpay.company.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthenticationResponse(null, ex.getMessage()));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthenticationResponse(null, ex.getMessage()));
        }
    }

//    @GetMapping("/user-details/{id}")
//    public ResponseEntity<?> getUserDetails(@PathVariable("id") Integer id,
//                                            @AuthenticationPrincipal UserDetails userDetails) {
//
//        User user = userRepository.findById(id);
//
//        if (user != null && userDetails != null && userDetails.getUsername().equals(user.getUsername())) {
//            return ResponseEntity.ok(user);
//        } else {
//            String errorMessage = "User details not found";
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new ErrorResponse(errorMessage));
//        }
//    }
}
