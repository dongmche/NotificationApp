package com.app.notification.repository;

import com.app.notification.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    @Query("SELECT c FROM Customer c " +
            "LEFT JOIN FETCH c.addresses " +
            "LEFT JOIN FETCH c.preferences")
    List<Customer> findAllCustomersWithDetails();


    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> findCustomersByName(@Param("name") String name);

    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:query% OR c.email LIKE %:query%")
    List<Customer> findCustomerByQuery(@Param("query") String query);

    @Query("SELECT DISTINCT c FROM Customer c JOIN preferences p WHERE p.preferenceType = :type")
    List<Customer> findCustomerByPreferenceType(@Param("type") String type);


//    @Query("SELECT DISTINCT c FROM Customer c JOIN c.addresses a WHERE a.type = :type")
//    List<Customer> findCustomersByAddressType(@Param("addressType") String type);

}
