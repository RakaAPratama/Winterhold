package com.winterhold.mvc.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGridDTO implements Serializable {
    private String id;
    private String fullName;
    private String membershipExpireDate;

    public static List<CustomerGridDTO> convertCustomerGrid(List<CustomerDTO> contentCustomer){
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<CustomerGridDTO> result = new ArrayList<>();

        for (CustomerDTO customer : contentCustomer){
            result.add(new CustomerGridDTO(
                    customer.getId(),
                    customer.getFullName(),
                    customer.getMembershipExpireDate().format(indoFormat)
            ));
        }

        return result;
    }
}
