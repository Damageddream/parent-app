package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.MedicationDto;
import bg.enterprise.parent_app.model.dto.PrescriptionDto;
import bg.enterprise.parent_app.model.entity.Medication;
import bg.enterprise.parent_app.model.entity.Prescription;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    MedicationDto toDTO(Medication entity);

    Medication toEntity(MedicationDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MedicationDto medicationDto, @MappingTarget Medication medication);
}