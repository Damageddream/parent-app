package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.model.dto.DosageLogDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.DosageLog;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DosageLogMapper {

    DosageLogDto toDTO(DosageLog entity);

    DosageLog toEntity(DosageLogDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DosageLogDto dosageLogDto, @MappingTarget DosageLog dosageLog);
}