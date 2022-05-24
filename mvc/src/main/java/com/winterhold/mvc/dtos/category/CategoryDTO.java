package com.winterhold.mvc.dtos.category;

import com.winterhold.mvc.models.Category;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class CategoryDTO implements Serializable {
    private final String id;
    private final Integer floor;
    private final String isle;
    private final String bay;
    private final Integer books;

    public static List<CategoryDTO> convert(List<Category> categories){
        List<CategoryDTO> result = new ArrayList<>();

        for (Category category : categories){
            result.add(new CategoryDTO(
                    category.getId(),
                    category.getFloor(),
                    category.getIsle(),
                    category.getBay(),
                    category.getBooks().size()
            ));
        }
        return result;
    }
}
