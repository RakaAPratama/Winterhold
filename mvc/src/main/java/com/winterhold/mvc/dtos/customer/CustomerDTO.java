package com.winterhold.mvc.dtos.customer;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CustomerDTO implements Serializable {
    private final String id;
    private final String fullName;
    private final LocalDate membershipExpireDate;
}
