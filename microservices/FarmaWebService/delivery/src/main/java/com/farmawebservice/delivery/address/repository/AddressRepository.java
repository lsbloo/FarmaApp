package com.farmawebservice.delivery.address.repository;

import com.farmawebservice.delivery.address.model.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO userx_user_addresses (userx_id,user_addresses_id) VALUES (?1, ?2)")
    void saveAddressByUserId(Long user_id, Long address_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM userx_user_addresses where userx_id=?1 and user_addresses_id=?2")
    void deleteAddress(Long user_id, Long user_address_id);

}
