package com.example.customer_service.dto;

import com.example.customer_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM Address  a WHERE a.customer.id= :customerId")
    List<Address> getAddressByCustomerId(@Param("customerId") int customerId);

    @Query("SELECT a FROM Address  a WHERE a.id = :addressId AND a.customer.id= :customerId")
    Optional<Address> getAddressCustomerAndAddressId(@Param("customerId") int customerId,
                                           @Param("addressId") int addressId);

    @Query("DELETE FROM address WHERE id = :addressId AND customer_id = :customerId")
    void deleteCustomerAddress(@Param("customerId") int customerId, @Param("addressId") int addressId);
}
