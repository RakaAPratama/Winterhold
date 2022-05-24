package com.winterhold.mvc.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInsertCategoryDTO implements Serializable {
    @NotBlank(message = "Id tidak boleh kosong")
    private String id;
    @NotNull(message = "Floor tidak boleh kosong")
    private Integer floor;
    @NotBlank(message = "Isle tidak boleh kosong")
    private String isle;
    @NotBlank(message = "Bay tidak boleh kosong")
    private String bay;
}
