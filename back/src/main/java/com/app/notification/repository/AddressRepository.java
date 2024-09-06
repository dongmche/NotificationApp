package com.app.notification.repository;

import com.app.notification.entity.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {

//    @Transactional
//    @Query("DELETE FROM addresses a WHERE a.id = :id")
//    void deleteAddressById(@Param("id") Long id);
}
