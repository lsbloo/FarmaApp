package com.poc.FarmaLoginService.repository;


import com.poc.FarmaLoginService.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserAuth, Long> {

    Optional<UserAuth> findByEmail(String email);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update user_auth set client_id_token=?1 where id=?2")
    void updateUser(String clientID, Long id);
}
