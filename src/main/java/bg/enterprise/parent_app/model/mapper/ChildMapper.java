package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.Parent;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ChildMapper {

    @Mapping(source = "parent.id", target = "parentId")
    ChildDto toDTO(Child entity);


    @Mapping(source = "parentId", target = "parent.id")
    Child toEntity(ChildDto dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ChildDto childDto, @MappingTarget Child child);
}