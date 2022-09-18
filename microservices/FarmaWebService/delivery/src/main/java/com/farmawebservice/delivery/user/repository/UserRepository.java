package com.farmawebservice.delivery.user.repository;

import com.farmawebservice.delivery.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserAuthId(Long userAuthId);
}
