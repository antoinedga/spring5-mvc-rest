package guru.springfamework.api.v1.mappers;


import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO categortToCategoryDTO(Category category);
}
