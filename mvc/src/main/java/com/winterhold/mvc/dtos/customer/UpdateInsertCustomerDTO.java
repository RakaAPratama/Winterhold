package com.winterhold.mvc.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInsertCustomerDTO implements Serializable {
    @NotBlank(message = "Id tidak boleh kosong")
    private String id;
    @NotBlank(message = "FirstName tidak boleh kosong")
    private String firstName;
    private String lastName;
    @NotNull(message = "BirthDate tidak boleh kosong")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotNull(message = "Gender tidak boleh kosong")
    private String gender;
    private String phone;
    private String address;
    private LocalDate membershipExpireDate;
}
