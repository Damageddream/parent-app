package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.model.entity.Child;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChildMapper {

    ChildDto toDTO(Child entity);

    Child toEntity(ChildDto dto);
}