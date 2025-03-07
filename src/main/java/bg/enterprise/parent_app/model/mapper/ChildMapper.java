package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.Parent;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ChildMapper {

    ChildDto toDTO(Child entity);

    Child toEntity(ChildDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ChildDto childDto, @MappingTarget Child child);
}