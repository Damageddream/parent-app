package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.entity.Parent;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    ParentDto toDTO(Parent entity);

    Parent toEntity(ParentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ParentDto parentDto, @MappingTarget Parent parent);
}