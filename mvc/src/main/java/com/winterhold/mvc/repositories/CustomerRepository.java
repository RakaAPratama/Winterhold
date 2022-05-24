package com.winterhold.mvc.repositories;

import com.winterhold.mvc.dtos.customer.CustomerDTO;
import com.winterhold.mvc.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("""
            SELECT new com.winterhold.mvc.dtos.customer.CustomerDTO
            (cus.id, cus.fullName, cus.membershipExpireDate)
            FROM Customer cus
            WHERE cus.id LIKE %:id%
            AND cus.fullName LIKE %:fullName%
            """)
    Page<CustomerDTO> findAllCustomer(String id, String fullName, Pageable pageable);
}
