package lk.directpay.company.controller;

import lk.directpay.company.auth.ErrorResponse;
import lk.directpay.company.entities.AppUser;
import lk.directpay.company.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserRepository appUserRepository;

    @GetMapping("/user-details/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable("id") String id,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(id);

        if (appUserOptional.isPresent() && userDetails != null && userDetails.getUsername().equals(appUserOptional.get().getUserName())) {
            AppUser appUser = appUserOptional.get();
            return ResponseEntity.ok(appUser);
        } else {
            String errorMessage = "User details not found";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(errorMessage));
        }
    }

}
