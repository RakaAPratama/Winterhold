package com.winterhold.mvc.services;

import com.winterhold.mvc.dtos.customer.CustomerDTO;
import com.winterhold.mvc.dtos.customer.UpdateInsertCustomerDTO;
import com.winterhold.mvc.models.Customer;
import com.winterhold.mvc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final int PAGE_LIMIT = 5;

    public Page<CustomerDTO> findByIdAndName(String id, String fullName, int page){
        return customerRepository.findAllCustomer(id, fullName, PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id")));
    }

    public UpdateInsertCustomerDTO findByCustomerId(String customerId){
        return customerRepository.findById(customerId)
                .map(customer -> new UpdateInsertCustomerDTO(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getBirthDate(),
                        customer.getGender(),
                        customer.getPhone(),
                        customer.getAddress(),
                        customer.getMembershipExpireDate()
                        ))
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    public void saveCustomer(UpdateInsertCustomerDTO customer){
        LocalDate exp = LocalDate.now().plusYears(2);
        Customer newCustomer = new Customer(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getGender(),
                customer.getPhone(),
                customer.getAddress(),
                exp
        );
        customerRepository.save(newCustomer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public void extendCustomer(String id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Customer not found"));
        customer.setMembershipExpireDate(customer.getMembershipExpireDate().plusYears(2));
        customerRepository.save(customer);
    }
}
