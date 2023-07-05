package lk.directpay.company.repositories;

import lk.directpay.company.entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser,String> {

    Optional<AppUser> findById(String id);
}
