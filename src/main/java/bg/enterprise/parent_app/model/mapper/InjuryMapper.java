package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.InjuryDto;
import bg.enterprise.parent_app.model.dto.MedicationDto;
import bg.enterprise.parent_app.model.entity.Injury;
import bg.enterprise.parent_app.model.entity.Medication;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InjuryMapper {

    InjuryDto toDTO(Injury entity);

    Injury toEntity(InjuryDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(InjuryDto injuryDto, @MappingTarget Injury injury);
}