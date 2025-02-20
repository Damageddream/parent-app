package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.entity.Parent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    ParentDto toDTO(Parent entity);

    Parent toEntity(ParentDto dto);
}