package com.example.customer_service.dto;

import com.example.customer_service.entity.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM Address  a WHERE a.customer.id= :customerId")
    List<Address> getAddressByCustomerId(@Param("customerId") int customerId);

    @Query("SELECT a FROM Address a WHERE a.customer.id= :customerId AND a.id=:addressId")
    Optional<Address> getAddressCustomerAndAddressId(@Param("customerId") Integer customerId,
                                                     @Param("addressId") Integer addressId);



    @Modifying
    @Transactional
    @Query("DELETE FROM Address a WHERE a.id = :addressId AND a.customer.id = :customerId")
    void deleteCustomerAddress(@Param("customerId") int customerId, @Param("addressId") int addressId);
}
